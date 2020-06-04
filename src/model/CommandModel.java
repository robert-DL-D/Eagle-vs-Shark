package model;

import java.util.List;

import java.util.ArrayList;





class CommandModel{
	 private List<Command> commandList = new ArrayList<Command>();
	 public CommandModel(){
	 }
	 private Command currentCommand = null;
	 public void setCommand(Command cmd){
	   currentCommand  = cmd;
	   cmd.execute();
	   commandList.add(cmd);
	 }
//	 public void undoAll(){
//	    for(Command cmd : commandList){cmd.undo();}
//	}
	 public void undo1(){
	 commandList.remove(commandList.size()-1);
	 currentCommand = commandList.get(commandList.size()-1);
	}
	 public void undo2(){
		 commandList.remove(commandList.size()-2);
		 currentCommand = commandList.get(commandList.size()-2);
		}
	 public void undo3(){
		 commandList.remove(commandList.size()-3);
		 currentCommand = commandList.get(commandList.size()-3);
		}
	
}
