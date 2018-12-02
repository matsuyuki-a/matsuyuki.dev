#!/usr/bin/env ruby

file_list = Dir.glob( "*.dat")

file_list.each { |name|
  file = File.open( "#{name.split(".")[0]}.html", "w" )
  content = <<-EOM
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <title>World Wide Adventure Wing</title>
    <link rel="stylesheet" href="style.css" type="text/css" />

    <!--  WWA requirements-->
    <link rel="stylesheet" href="wwa.css" type="text/css" />
    <script src="audio/audio.min.js"></script>
    <script src="wwa.js"></script>
    <!-- /WWA requirements -->

</head>

<body>
    <div id="wrapper">
        <!-- WWA -->
        <div id="wwa-wrapper" class="wwa-size-box"
             data-wwa-mapdata="#{name}"
             data-wwa-loader="wwaload.js"
             data-wwa-urlgate-enable="true"
             data-wwa-title-img="cover.gif">
        </div>
        <!-- /WWA -->
    </div>
    <footer id="copyright">
        <p> Internet RPG &quot;<a href="http://www.wwajp.com" class="wwa-copyright">World Wide Adventure</a>&quot; &copy;1996-2015 NAO</p>
        <p> &quot;<a href="http://wwawing.com/" class="wwa-copyright">WWA Wing</a>&quot; &copy;2013-2015 WWA Wing Team</p>
        <p><a href="../">WWA一覧</a> | <a href="../../">まつひら系列の思い出</a></p>
    </footer>
</body>
</html>
  EOM
  file.print content
  file.close
}

