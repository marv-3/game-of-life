package domain

class Board(cs: List[Position]):

  def isAlive(pos: (Int, Int)): Boolean = cs.find(c => c.samePosition(pos)).get.isAlive

//  def isEmpty(pos: Position): Boolean = !isAlive(pos)

  def liveNeighbors(pos: Position): Int = pos.neighbors().count(t => isAlive(t))

  def update(pos: Position): Board = new Board(cs.map(x => if x.samePosition(pos) then pos else x))

  def update(poss: List[Position]): Board = poss.foldLeft(this)((b, pos) => b.update(pos))

  def nextGen(): Board = new Board(
    cs.map(c => (c, liveNeighbors(c)))
      .map((c, n) =>
        if c.isAlive && !List(2, 3).contains(n) then c.empty()
        else if c.isEmpty && n == 3 then c.birth()
        else c,
      ),
  )

  def nextGen2(): List[Position] =
    val as = cs
      .map(c => (c, liveNeighbors(c)))

    as.filter((c, n) => c.isAlive && (n == 2 || n == 3)).map((c, _) => c.empty())
    :::
    as.filter((c, n) => c.isEmpty && n == 3).map((c, _) => c.birth())
//          .map((c, _) => c.birth())
