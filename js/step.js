var step = function(){
  ctx.fillRect( 0, 0, width, height );

  keyStore.update();

  // 接地判定
  var onGround = false;
  for( var i = player.m_contactList; i; i = i.next ){
    // 一般のアクションゲームだとこの判定では弱い. 今回は床が下に1つなのでこれでOK.
    if( i.other == ground ){ 
      onGround = true;
      break;
    }
  }

  var v = player.GetLinearVelocity();
  var vx = v.x, vy = v.y;

  if( keyStore.state[ 37 ] == KeyState.DOWN || keyStore.state[ 37 ] == KeyState.PRESS ){
    player.WakeUp();
    vx = -100;
  }else if( keyStore.state[ 39 ] == KeyState.DOWN || keyStore.state[ 39 ] == KeyState.PRESS ){
    player.WakeUp();
    vx =  100;
  }else{
    vx = 0;
  }

  // JUMP
  if( onGround && keyStore.state[ 90 ] == KeyState.DOWN ){
    player.WakeUp();
    vy -= 300;
  }

  player.SetLinearVelocity( new b2Vec2( vx, vy ) );

  world.Step( 1.0 / 60, 1 );
  ctx.clearRect( 0, 0, width, height );
  ctx.fillStyle = "#000000";
  ctx.fillRect( 0, 0, width, height );
  drawWorld( world, ctx );
  /*
     for( var b = world.m_bodyList; b; b = b.m_next ){
     ctx.fillColor = "#FFFFFF";
     ctx.beginPath();
     ctx.arc( b.m_position.x, b.m_position.y, 2, 0, Math.PI * 2, true );
     ctx.fill();
     }
     */
  setTimeout( step, 10 );
};
