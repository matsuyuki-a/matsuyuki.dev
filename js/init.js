var init = function(){
  gameState = State.INIT;
  canvas = $( "screen" );
  ctx = canvas.getContext( "2d" );
  width = canvas.getAttribute( "width" );
  height = canvas.getAttribute( "height" );
  keyStore = new KeyStore();

  worldAABB = new b2AABB();
  worldAABB.minVertex.Set( -1000, -1000 );
  worldAABB.maxVertex.Set(  1000,  1000 );
  gravity = new b2Vec2( 0, 300 );
  doSleep = true;
  world = new b2World( worldAABB, gravity, doSleep );

  // 円
  var circleSd = new b2CircleDef();
  circleSd.density = 1.0;     // 密度
  circleSd.radius = 20;       // 半径
  circleSd.restitution = 0;   // 弾性
  circleSd.friction = 0;      // 摩擦
  var circleBd = new b2BodyDef();
  circleBd.AddShape( circleSd );
  circleBd.position.Set( 100, 100 );
  player = world.CreateBody(circleBd);

  // 床
  var groundSd = new b2BoxDef();
  groundSd.extents.Set( 2000, 50 );
  groundSd.restitution = 0; 
  var groundBd = new b2BodyDef();
  groundBd.AddShape( groundSd );
  groundBd.position.Set( -500, 500 );
  ground = world.CreateBody( groundBd );
  
  // ブロック落下テスト
  var blockSd = new b2BoxDef();
  blockSd.density = 100.0;
  blockSd.extents.Set( 30, 30 );
  blockSd.restitution = 0.2; // 反発力
  var blockBd = new b2BodyDef();
  blockBd.AddShape( blockSd );
  blockBd.position.Set( 600, -400 );
  setTimeout( function(){
    block[ 0 ] = world.CreateBody( blockBd );
  }, 2500 );

  gameState = State.USER;
  setTimeout( step, 10 );
};

