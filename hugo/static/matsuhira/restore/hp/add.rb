#!/usr/bin/ruby

while !STDIN.eof
  line = STDIN.gets
  puts "wget #{line}"
end
