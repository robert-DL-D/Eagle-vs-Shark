package model;

import java.util.List;

import java.util.ArrayList;





class CommandModel{
	 private List<Command> commands = new ArrayList<Command>();
	 public CommandModel(){
	 }
	 private Command currentCommand = null;
	 public void setCommand(Command cmd){
	   currentCommand  = cmd;
	   cmd.execute();
	   commands.add(cmd);
	 }
	 public void undoAll(){
	    for(Command cmd : commands){cmd.undo();}
	}
	 public void undo(){
	 commands.remove(commands.size()-1);
	 currentCommand = commands.get(commands.size()-1);
	}

	
}
