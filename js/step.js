var step = function(){
  keyStore.update();
  switch( gameState ){
    case State.USER: 
      // 接触判定
      var onGround = false;
      for( var i = player.m_contactList; i; i = i.next ){
        // 一般のアクションゲームだとこの判定では弱い. 今回は床が下に1つなのでこれでOK.
        if( i.other == ground ){ // 接地判定
          onGround = true;
          break;
        }else{
          for( var j = 0; j < enemyList.length; j++ ){
            if( i.other == enemyList[ j ] ){ // 死亡当たり判定
             // gameState = State.GAMEOVER;
              world.DestroyBody( player );
              playerDeathData.x = player.m_position.x;
              playerDeathData.y = player.m_position.y;
              playerDeathData.afterTime = 0;
              player = null;
              gameState = State.GAMEOVER
              break;
            }
          }
        }
      }
      if( gameState == State.USER ){
        // 左右移動
        var v = player.GetLinearVelocity();
        if( keyStore.state[ 37 ] == KeyState.DOWN || keyStore.state[ 37 ] == KeyState.PRESS ){
          player.WakeUp();
          v.x = -100;
        }else if( keyStore.state[ 39 ] == KeyState.DOWN || keyStore.state[ 39 ] == KeyState.PRESS ){
          player.WakeUp();
          v.x =  100;
        }else{
          v.x = 0;
        }

        // ジャンプ
        if( onGround && keyStore.state[ 90 ] == KeyState.DOWN ){
          player.WakeUp();
          v.y -= 300;
        }

        // 速度の確定
        player.SetLinearVelocity( new b2Vec2( v.x, v.y ) );

        world.Step( 1.0 / 60, 1 );

        // 点Q
        if( player.m_position.x > 1000 && !pointQAppeared ){
          pointQ = world.CreateBody( pointQBd );
          enemyList.push( pointQ );
          pointQ.SetLinearVelocity( new b2Vec2( -768, 0 ) );
          pointQAppeared = true;
        }

        // カメラ
        camera.x = player.m_position.x -  width / 2;
        camera.y = player.m_position.y - height * 2 / 3;
      }

      break;
    case State.GAMEOVER:

      playerDeathData.afterTime++;
      world.Step( 1.0 / 60, 1 );
      break;
  }
  
  // 描画
  ctx.clearRect( 0, 0, width, height );
  ctx.fillStyle = "#FFFFFF";
  ctx.fillRect( 0, 0, width, height );

  drawWorld( world, ctx, camera.x, camera.y );
  
  // 点 P(layer)
  if( player ){
    var pdx = player.m_position.x - camera.x;
    var pdy = player.m_position.y - camera.y;
    ctx.fillStyle = "#000000";
    ctx.beginPath();
    ctx.arc( pdx, pdy, 20, 0, Math.PI * 2, true ); 
    ctx.fill();
    ctx.font = "35px Sans-serif";
    ctx.fillText( "P", pdx - 30, pdy - 20 );
  }

  // 点P 死亡エフェクト
  if( gameState == State.GAMEOVER && playerDeathData.afterTime < 25 ){
    var alpha = 1 - playerDeathData.afterTime / 25 ;
    var x = playerDeathData.x - camera.x;
    var y = playerDeathData.y - camera.y;
    ctx.fillStyle = "rgba( 0, 0, 0, " + alpha + " )";
    ctx.beginPath();
    ctx.arc( x, y, 20, 0, Math.PI * 2, true ); 
    ctx.fill();
    ctx.beginPath();

    ctx.fillStyle = "rgba( 0, 0, 0, " + alpha + " )";
    ctx.arc( x, y, playerDeathData.afterTime * 20, 0, Math.PI * 2, true ); 
    ctx.stroke();

    ctx.font = "35px Sans-serif";
    ctx.fillText( "P", x - 30, y - 20 );
    ctx.fillStyle = "#000000";
  }

  // 点 Q
  if( pointQ ){
    var qdx = pointQ.m_position.x - camera.x;
    var qdy = pointQ.m_position.y - camera.y;
    ctx.fillStyle = "#000000";
    ctx.beginPath();
    ctx.arc( qdx, qdy, 20, 0, Math.PI * 2, true ); 
    ctx.fill();
    ctx.font = "35px Sans-serif";
    ctx.fillText( "Q", qdx - 30, qdy - 20 );
  }

  // 線分AB
  var gdx1 = camera.x > 0 ? 0 : - camera.x;
  var gdx2 = 2000 - camera.x > width ? width : 2000 - camera.x;
  var gdy = ground.m_position.y - camera.y - 20 - 50;
  ctx.moveTo( gdx1, gdy );
  ctx.lineTo( gdx2, gdy );
  ctx.stroke();
  ctx.font = "35px Sans-serif";

  ctx.fillStyle = "#000000";
  ctx.fillText( "A",      - camera.x - 35, gdy + 15 );
  ctx.fillText( "B", 2000 - camera.x +  5 , gdy + 15 );

  // タイトル
  drawRectInCanvas( ctx, 50, 50, camera.x, camera.y, 500, 300 );
  drawStringInCanvas( ctx, "DC概論 121122/5h", 75, 100, camera.x, camera.y, "35px Sans-serif" );
  drawStringInCanvas( ctx, "線分ABを点Pが動くゲーム", 75, 200, camera.x, camera.y, "35px Sans-serif" );
  drawStringInCanvas( ctx, "作: @rmn_e  (当時@rmn_31415)", 75, 250, camera.x, camera.y, "35px Sans-serif" );
  drawStringInCanvas( ctx, "Powered by Box2DJS", 200, 320, camera.x, camera.y, "30px Sans-serif" );

  // 説明1 
  drawRectInCanvas( ctx, 50 + width, 50, camera.x, camera.y, 500, 300 );
  drawStringInCanvas( ctx, "なにこれ", 75 + width, 100, camera.x, camera.y, "35px Sans-serif" );
  drawStringInCanvas( ctx, "Box2DJSというブラウザ上で", 75 + width, 200, camera.x, camera.y, "30px Sans-serif" );
  drawStringInCanvas( ctx, "動く物理エンジンがあったので,  ", 75 + width, 250, camera.x, camera.y, "30px Sans-serif" );
  drawStringInCanvas( ctx, "それでゲーム作ってみた.", 75 + width, 300, camera.x, camera.y, "30px Sans-serif" );

  // 説明2
  drawRectInCanvas( ctx, 50 + 2*width, 50, camera.x, camera.y, 500, 300 );
  drawStringInCanvas( ctx, "Box2DJSって", 75 + 2*width, 100, camera.x, camera.y, "35px Sans-serif" );
  drawStringInCanvas( ctx, "C++向けの物理エンジン\"Box2D\"", 75 + 2*width, 200, camera.x, camera.y, "30px Sans-serif" );
  drawStringInCanvas( ctx, "のJavaScript移植版.", 75 + 2*width, 250, camera.x, camera.y, "30px Sans-serif" );
  drawStringInCanvas( ctx, "ブラウザ上で動くよ!", 75 + 2*width, 300, camera.x, camera.y, "30px Sans-serif" );
  if( gameState == State.GAMEOVER ){
    ctx.fillStyle = "rgba( 255, 0, 0, 0.3 )";
    ctx.fillRect( 0, 0, width, height );
    ctx.fillStyle = "#FFFFFF";
    ctx.font = "100px Sans-serif";
    ctx.fillText( "GAME OVER", 65, 200 );
  }

  setTimeout( step, 10 );

};
