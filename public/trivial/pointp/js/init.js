var init = function(){
  gameState = State.INIT;
  canvas = $( "screen" );
  ctx = canvas.getContext( "2d" );
  width = parseInt( canvas.getAttribute( "width" ) );
  height = parseInt( canvas.getAttribute( "height" ) );
  keyStore = new KeyStore();

  worldAABB = new b2AABB();
  worldAABB.minVertex.Set(  -100,  -500 );
  worldAABB.maxVertex.Set( STAGE_LENGTH + 100,  1000 );
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
  pointQBd.position.Set( 2000, 420 );

  // 床
  var groundSd = new b2BoxDef();
  groundSd.extents.Set( STAGE_LENGTH + 150, 50 );
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
  wallBd.position.Set( STAGE_LENGTH + 50, 0 );
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

  // ブロック落下
  var blockSd = new b2BoxDef();
  blockSd.density = 100.0;
  blockSd.extents.Set( 30, 30 );
  blockSd.restitution = 0.2; // 反発力
  var blockBd = new b2BodyDef();
  blockBd.AddShape( blockSd );
  blockBd.position.Set( 0, -400 );
  setTimeout( function(){
    var tmp =  world.CreateBody( blockBd );
    block.push( tmp );
    enemyList.push( tmp );
  }, 2500 );

  // とげ 
  var togeSd = new b2PolyDef();
  var radius = 16;
  togeSd.restitution = 0; 
  togeSd.density = 0.0001;
  togeSd.vertices = new Array();
  for( var i = 0; i < 3; i++ ){
    togeSd.vertices[ i ] = new b2Vec2(
      radius * Math.cos( Math.PI * 2 * i / 3 + Math.PI / 2 ) ,
      radius * Math.sin( Math.PI * 2 * i / 3 + Math.PI / 2 ) 
    );
  }
  togeSd.vertexCount = 3;
  var togeBd = new b2BodyDef();
  togeBd.AddShape( togeSd );
  for( var i = 0; i < 7; i++ ){
    togeBd.position.Set( 500 + i * radius * 6, 0 );
    toge.push({
      obj:  world.CreateBody( togeBd ),
      fall: false
    });
    enemyList.push( toge[ i ].obj );
  }

  //  空中三角形
  var triSd = new b2PolyDef();
  var radius = 32;
  triSd.restitution = 0;
  triSd.density = 0.01;
  triSd.vertices = new Array();
  for( var i = 0; i < 3; i++ ){
    triSd.vertices[ i ] = new b2Vec2(
      radius * Math.cos( Math.PI * 2 * i / 3 + Math.PI / 2 ) ,
      radius * Math.sin( Math.PI * 2 * i / 3 + Math.PI / 2 ) 
    );
  }
  triSd.vertexCount = 3;
  var triBd = new b2BodyDef();
  triBd.AddShape( triSd );
  for( var i = 0; i < 3; i++ ){
    switch( i ){
      case 0:
        triBd.position.Set( 2300, 200 );
        break;
      case 1:
        triBd.position.Set( 2600, 50 );
        break;
      case 2 :
        triBd.position.Set( 2900, 200 );
        break;
    }
    tri.push({
      obj:  world.CreateBody( triBd ),
      fall: false
    });
  }

  // スーパーボール軍団 
  var spSd = new b2PolyDef();
  var radius = 10;
  spSd.density = 1.0;     // 密度
  spSd.radius = 10;       // 半径
  spSd.restitution = 1.0;   // 弾性
  spSd.friction = 1.0;       // 摩擦
  spSd.vertices = new Array();
  for( var i = 0; i < 24; i++ ){
    spSd.vertices[ i ] = new b2Vec2(
      radius * Math.cos( Math.PI * 2 * i / 24 + Math.PI / 2 ) ,
      radius * Math.sin( Math.PI * 2 * i / 24 + Math.PI / 2 ) 
    );
  }
  spSd.vertexCount = 24;
  spBd = new b2BodyDef();
  spBd.AddShape( spSd );

  gameState = State.USER;

  setTimeout( step, 10 );
};

