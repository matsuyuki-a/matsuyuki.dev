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
        }else if( i.other == block[ 0 ] ){ // 死亡当たり判定 (仮)
          gameState = State.GAMEOVER;
          break;
        }
      }

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
      break;
    case State.GAMEOVER:


      break;
  }

  // 描画
  var camera = {
    x: player.m_position.x -  width / 2,
    y: player.m_position.y - height * 2 / 3
  };
  ctx.clearRect( 0, 0, width, height );
  ctx.fillStyle = "#FFFFFF";
  ctx.fillRect( 0, 0, width, height );
  
  drawWorld( world, ctx, camera.x, camera.y );
  if( gameState == State.GAMEOVER ){
    ctx.fillStyle = "rgba( 255, 0, 0, 0.3 )";
    ctx.fillRect( 0, 0, width, height );
  }
  
  // 点 P(layer)
  var pdx = player.m_position.x - camera.x;
  var pdy = player.m_position.y - camera.y;
  ctx.fillStyle = "#000000";
  ctx.beginPath();
  ctx.arc( pdx, pdy, 20, 0, Math.PI * 2, true ); 
  ctx.fill();
  ctx.font = "35px Sans-serif";
  ctx.fillText( "P", pdx - 30, pdy - 20 );

  // 線分AB
  var gdx1 = camera.x > 0 ? 0 : - camera.x;
  var gdx2 = 2000 - camera.x > width ? width : 2000 - camera.x;
  var gdy = ground.m_position.y - camera.y - 20 - 50;
  ctx.moveTo( gdx1, gdy );
  ctx.lineTo( gdx2, gdy );
  ctx.stroke();
  ctx.font = "35px Sans-serif";
  ctx.fillText( "A",      - camera.x - 35, gdy + 15 );
  ctx.fillText( "B", 2000 - camera.x +  5 , gdy + 15 );

  // タイトル
  
  setTimeout( step, 10 );
};
