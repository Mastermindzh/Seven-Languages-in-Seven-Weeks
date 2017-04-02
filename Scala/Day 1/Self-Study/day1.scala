/*

Find:
• The Scala API

http://www.scala-lang.org/api/current/

• A comparison of Java and Scala

https://www.toptal.com/scala/why-should-i-learn-scala

• A discussion of val versus var

http://www.scala-lang.org/old/node/5367


Do:
• Write a game that will take a tic-tac-toe board with X, O, and blank
characters and detect the winner or whether there is a tie or no
winner yet. Use classes where appropriate.

• Bonus problem: Let two players play tic-tac-toe

*/

object TicTacToe {

  var currentPlayer = 'X';
  val playerOne = 'X'
  val playerTwo = 'O'


  val board = Array(
    Array('_', '_', '_'),
    Array('_', '_', '_'),
    Array('_', '_', '_')
  )

  /**
    * Flips current player to the other player.
    */
  def flipPlayers = {
    if(currentPlayer.equals(playerOne)){
      currentPlayer = playerTwo
    }else{
      currentPlayer = playerOne
    }
  }

  /**
    * read user input and play a move
    * @param error set to true if user made a mistake and has to re-enter his position
    */
  def readLine(error : Boolean = false) : Unit = {
    if(error){
      System.err.println("Please enter a value ON the grid which has no mark yet.")
    }

    println("Please enter the x position you want to place your mark at")
    val x = scala.io.StdIn.readLine().toInt

    println("Please enter the y position you want to place your mark at")
    val y = scala.io.StdIn.readLine().toInt

    if((x > 0 && x < 4) && (y > 0 && y < 4) ){
      play(x,y)
    }else{
      readLine(true)
    }
  }

  /**
    * checks whether pos(x,y) is a valid position
    * if it's valid it will put currentPlayer on that position
    * if it isn't it will ask the user for new input using the readLine method
    * @param x coordinate
    * @param y coordinate
    */
  def play(x : Int, y : Int) = {
    if(board(x - 1)(y - 1).equals('_')){
      board(x - 1)(y - 1) = currentPlayer
      flipPlayers
    }else{
     readLine(true)
    }
  }

  /**
    * Checks whether the board is full
    * @return true if board is full, else false
    */
  def boardIsFull : Boolean = {
    board.foreach { row =>
        // if any row contains an underscore we're obv not full yet so we can return false;
        if(row contains '_'){
          return false;
        }

    }
    return true;
  }

  /**
    * main game loop
    */
  def run = {
    while(!boardIsFull && !boardHasWinner){
      printBoard
      readLine()
      if(boardHasWinner){
        flipPlayers // reverse last player flip
        println("Congrats " + currentPlayer + " you have won this game of Tic-Tac-Toe")
      }
    }
    if(!boardHasWinner){
      println("Sorry, it's a draw!")
    }
    // print the board once more so the players can see the final score
    printBoard
    // exit execution
    System.exit(0)
  }

  /**
    * Checks whether the board has a winner
    * @return true if board has a winner else false.
    */
  def boardHasWinner : Boolean = {

    //check horizontals
    board.foreach {row =>
      if(row(0) != '_' && row.forall(c => c.equals(row(0)))){
        return true
      }
    }

    // check diagonals
    val topLeftBottomRight = Array(board(0)(0), board(1)(1), board(2)(2))
    val bottomLeftTopRight = Array(board(0)(2), board(1)(1), board(2)(0))

    if(topLeftBottomRight(0) != '_' && topLeftBottomRight.forall(c => c.equals(topLeftBottomRight(0)))){
      return true
    }
    if(bottomLeftTopRight(0) != '_' && bottomLeftTopRight.forall(c => c.equals(bottomLeftTopRight(0)))){
      return true
    }
    // if no winner has been found, return false.
    return false
  }

  /**
    * Prints a visual representation of the board and who's turn it is
    */
  def printBoard = {
    board.foreach { row => println("" + row(0) + " | "
      + row(1) + " | "
      + row(2) )}

    println(currentPlayer + " is playing")
  }

}

// let's play!
TicTacToe.run