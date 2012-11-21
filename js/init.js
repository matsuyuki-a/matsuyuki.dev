var init = function(){
  gameState = State.INIT;
  canvas = $( "screen" );
  ctx = canvas.getContext( "2d" );
  width = parseInt( canvas.getAttribute( "width" ) );
  height = parseInt( canvas.getAttribute( "height" ) );
  keyStore = new KeyStore();

  worldAABB = new b2AABB();
  worldAABB.minVertex.Set(  -100,  -500 );
  worldAABB.maxVertex.Set(  2100,  1000 );
  gravity = new b2Vec2( 0, 300 );
  doSleep = true;
  world = new b2World( worldAABB, gravity, doSleep );

  // 点P
  var circleSd = new b2CircleDef();
  circleSd.density = 1.0;     // 密度
  circleSd.radius = 20;       // 半径
  circleSd.restitution = 0;   // 弾性
  circleSd.friction = 0;      // 摩擦
  var circleBd = new b2BodyDef();
  circleBd.AddShape( circleSd );
  circleBd.position.Set( 20, 100 );
  player = world.CreateBody(circleBd);

  // 点Q
  pointQBd = new b2BodyDef();
  pointQBd.AddShape( circleSd );
  pointQBd.position.Set( 2000, 500 );

  // 床
  var groundSd = new b2BoxDef();
  groundSd.extents.Set( 2150, 50 );
  groundSd.restitution = 0; 
  var groundBd = new b2BodyDef();
  groundBd.AddShape( groundSd );
  groundBd.position.Set( -50, 500 );
  ground = world.CreateBody( groundBd );
  
  // 壁
  var wallSd = new b2BoxDef();
  wallSd.extents.Set( 50, 1000 );
  wallSd.restitution = 0; 
  var wallBd = new b2BodyDef();
  wallBd.AddShape( wallSd );
  wallBd.position.Set( -50, 0 );
  leftWall = world.CreateBody( wallBd );
  wallBd.position.Set( 2050, 0 );
  rightWall = world.CreateBody( wallBd );
 /* 
  //(・ω・
  var doonSd = new b2PolyDef();
  doonSd.density = 100.0;
  var poliN = 96;
  var radius = 96;
  doonSd.vertices = new Array(  );
  for( var i = 0; i < poliN; i++ ){
    doonSd.vertices[ i ] = new b2Vec2(
       2 *radius * Math.cos( Math.PI * 2 * i / poliN ) ,
      radius * Math.sin( Math.PI * 2 * i / poliN ) 
    );
  }
  doonSd.vertexCount = poliN;
  var doonBd = new b2BodyDef();
  doonBd.AddShape( doonSd );
  doonBd.position.Set( 500, 100 );
  setTimeout( function(){
    doon = world.CreateBody( doonBd );
  }, 1000 );
*/

  // ブロック落下テスト
  var blockSd = new b2BoxDef();
  blockSd.density = 100.0;
  blockSd.extents.Set( 30, 30 );
  blockSd.restitution = 0.2; // 反発力
  var blockBd = new b2BodyDef();
  blockBd.AddShape( blockSd );
  blockBd.position.Set( 600, -400 );
  setTimeout( function(){
    var tmp =  world.CreateBody( blockBd );
    block.push( tmp );
    enemyList.push( tmp );
  }, 2500 );
  gameState = State.USER;
  setTimeout( step, 10 );
};

