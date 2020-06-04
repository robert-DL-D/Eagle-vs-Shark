package model;

class MoveCommand 
extends AbstractCommand 
implements Command {


    
	public MoveCommand(MovablePiece movablePiece){
	    // new board state is validated
	  }
	  public void execute(){
	    // change the board state
	  }
	 public void undo(){ // restore
	 }

	}
