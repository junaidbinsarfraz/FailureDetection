package com.failuredetection.controller;

import java.util.List;

import com.failuredetection.model.Node;
import com.failuredetection.model.TreeNode;
import com.failuredetection.util.Constants;
import com.failuredetection.view.Main;

public class TrainingController {

	public TreeNode<Node> buildTree(final List<String> lines, Boolean isDetectionMode) {
		TreeNode<Node> root = null;

		Integer currentLevel = 0, index = 0;
		TreeNode<Node> currentNode = null;
		
		try {
			for (String line : lines) {

				String[] splittedStr = line.split(" ");

				// rootnode
				if (index == 0) {

					if (Constants.COMMAND_CALL.equals(splittedStr[1])) {
						Node node = new Node();

						node.setLevel(++currentLevel);
						node.setStartTime(Integer.parseInt(splittedStr[0]));
						node.setName(splittedStr[2]);

						root = new TreeNode<Node>(node);

						currentNode = root;
					} else {
						// TODO show error
					}

				} else {

					if (Constants.COMMAND_CALL.equals(splittedStr[1])) {

						Node node = new Node();

						node.setLevel(++currentLevel);
						node.setStartTime(Integer.parseInt(splittedStr[0]));
						node.setName(splittedStr[2]);

						TreeNode<Node> treeNode = new TreeNode<Node>(node);

						currentNode.addChild(treeNode);
						currentNode = treeNode;

					} else {

						Integer endTime = Integer.parseInt(splittedStr[0]);

						Node node = currentNode.getData();

						node.setEndTime(endTime);
						node.setExecutionTime(endTime - node.getStartTime());
						
						if(Boolean.TRUE.equals(isDetectionMode)) {
							// Check if its deviating/abnormal or not
							
							if((new Double(node.getExecutionTime())) < (Main.mean - 2 * Main.standardDeviation) || 
									(new Double(node.getExecutionTime())) > (Main.mean + 2 * Main.standardDeviation)) {
								
								return null;
								
							}
							
						}

						currentNode = currentNode.getParent();
						--currentLevel;
					}

				}

				index++;

			}
		} catch (Exception e) {
			return null;
		}

		return root;
	}
	
	public Boolean matchTree(List<String> lines1, List<String> lines2) {
		
		if(lines1.size() != lines2.size()) {
			return Boolean.FALSE;
		}
		
		for(int i = 0; i < lines1.size(); i++) {
			if(!(lines1.get(i).equals(lines2.get(i)))) {
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}

}
