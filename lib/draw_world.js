function drawWorld(world, context, cx, cy) {
  for (var j = world.m_jointList; j; j = j.m_next) {
    drawJoint(j, context, cx, cy);
  }
  for (var b = world.m_bodyList; b; b = b.m_next) {
    for (var s = b.GetShapeList(); s != null; s = s.GetNext()) {
      if( !DEBUG && (  b == ground || b == leftWall || b == rightWall ) ){ continue; }
      drawShape(s, context, cx, cy);
    }
  }
}
function drawJoint(joint, context, cx, cy) {
  var b1 = joint.m_body1;
  var b2 = joint.m_body2;
  var x1 = b1.m_position;
  var x2 = b2.m_position;
  var p1 = joint.GetAnchor1();
  var p2 = joint.GetAnchor2();

  p1.x -= cx; p1.y -= cy;
  p2.x -= cx; p2.y -= cy;
  x1.x -= cx; x1.y -= cy;
  x2.x -= cx; x2.y -= cy;

  context.strokeStyle = '#00eeee';
  context.beginPath();
  switch (joint.m_type) {
    case b2Joint.e_distanceJoint:
      context.moveTo(p1.x, p1.y);
      context.lineTo(p2.x, p2.y);
      break;

    case b2Joint.e_pulleyJoint:
      // TODO
      break;

    default:
      if (b1 == world.m_groundBody) {
        context.moveTo(p1.x, p1.y);
        context.lineTo(x2.x, x2.y);
      }
      else if (b2 == world.m_groundBody) {
        context.moveTo(p1.x, p1.y);
        context.lineTo(x1.x, x1.y);
      }
      else {
        context.moveTo(x1.x, x1.y);
        context.lineTo(p1.x, p1.y);
        context.lineTo(x2.x, x2.y);
        context.lineTo(p2.x, p2.y);
      }
      break;
  }
  context.stroke();
}
function drawShape(shape, context, cx, cy) {
  context.strokeStyle = '#000000';
  context.beginPath();
  switch (shape.m_type) {
    case b2Shape.e_circleShape:
      {
        var circle = shape;
        var pos = circle.m_position;
        var r = circle.m_radius;
        var segments = 16.0;
        var theta = 0.0;
        var dtheta = 2.0 * Math.PI / segments;
        // draw circle
        context.moveTo(pos.x + r - cx, pos.y - cy);
        for (var i = 0; i < segments; i++) {
          var d = new b2Vec2(r * Math.cos(theta), r * Math.sin(theta));
          var v = b2Math.AddVV(pos, d);
          context.lineTo(v.x - cx, v.y - cy);
          theta += dtheta;
        }
        context.lineTo(pos.x + r - cx, pos.y - cy);

        // draw radius
        context.moveTo(pos.x - cx, pos.y - cy);
        var ax = circle.m_R.col1;
        var pos2 = new b2Vec2(pos.x + r * ax.x, pos.y + r * ax.y);
        context.lineTo(pos2.x - cx, pos2.y - cy);
      }
      break;
    case b2Shape.e_polyShape:
      {
        var poly = shape;
        var tV = b2Math.AddVV(poly.m_position, b2Math.b2MulMV(poly.m_R, poly.m_vertices[0]));
        context.moveTo(tV.x - cx, tV.y - cy);
        for (var i = 0; i < poly.m_vertexCount; i++) {
          var v = b2Math.AddVV(poly.m_position, b2Math.b2MulMV(poly.m_R, poly.m_vertices[i]));
          context.lineTo(v.x - cx, v.y - cy);
        }
        context.lineTo(tV.x - cx, tV.y - cy);
      }
      break;
  }
  context.stroke();
}

