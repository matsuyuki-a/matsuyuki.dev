var KeyState = {
  NONE    : 0,
  DOWNNOW : 1, 
  DOWN    : 2, 
　PRESS   : 3,
  UPNOW   : 4,
  UP      : 5  
};

var KeyStore = function(){
  this.state = new Array( 256 );
  for( var i = 0; i < this.state.length; i++ ){
    this.state[ i ] = KeyState.NONE;
  }
};

KeyStore.prototype.setKeyState = function( keyCode, state ){
  this.state[ keyCode ] = state;
};

KeyStore.prototype.update = function(){
  for( var i = 0; i < this.state.length; i++ ){
    switch( this.state[ i ] ){
      case KeyState.DOWNNOW:
        this.state[ i ] = KeyState.DOWN;
        break;
      case KeyState.DOWN:
        this.state[ i ] = KeyState.PRESS;
        break;
      case KeyState.UPNOW:
        this.state[ i ] = KeyState.UP;
        break;
      case KeyState.UP:
        this.state[ i ] = KeyState.NONE;
        break;
    }
  }
}

window.onkeydown = function( e ){
  keyStore.setKeyState( e.keyCode, KeyState.DOWNNOW );
  return false;
};
window.onkeyup = function( e ){
  keyStore.setKeyState( e.keyCode, KeyState.UPNOW );
  return false;
};
