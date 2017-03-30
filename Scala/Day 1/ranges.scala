val range = 0 until 10;
println("Range starts at " + range.start + " and ends at " + range.end + ".")

println
val increments = 0 until 10 by 5;
increments.foreach { i => println(i) }

println
val direction = 10 until 0 by -3;
direction.foreach { i => println(i) }

println
val inclusive = 0 to 10 by 5;
inclusive.foreach { i => println(i) }

println
val chars = 'a' to 'c';
chars.foreach { c => println(c) }