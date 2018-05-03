package ch4examples;

import ch4.SeqStack;
import ch4.Stack;

public class Expression {
	public static StringBuffer toPrefix(String infix) {
		Stack<String> oprandStack = new SeqStack<String>(infix.length());
		StringBuffer prefix = new StringBuffer(infix.length()*2);
		
		// Scan expression from right to left
		int i = infix.length() - 1;
		while (i >= 0) {
			char ch = infix.charAt(i);
			switch(ch) {
				case '+': case '-':
					while (!oprandStack.isEmpty() && (oprandStack.peek().equals("*") || oprandStack.peek().equals("/")))
						prefix.insert(0, oprandStack.pop());
					oprandStack.push(ch+"");
					i--;
					break;
				case '*': case '/':
					oprandStack.push(ch+"");
					i--;
					break;
				case ')':
					oprandStack.push(ch+"");
					i--;
					break;
				case '(':
					String out = oprandStack.pop();
					while (out != null && !out.equals(")")) {
						prefix.insert(0, out);
						out = oprandStack.pop();
					}
					i--;
					break;
				default: 
					while (i >= 0 && ch >= '0' && ch <= '9') {
						prefix.insert(0, ch+"");
						i--;
						if (i >= 0)
							ch = infix.charAt(i);
					}
					prefix.insert(0, " ");
					
			}
			
		}
		while (!oprandStack.isEmpty())
			prefix.insert(0, oprandStack.pop());
		return prefix;
	}
	
	public static StringBuffer toPostfix(String infix) {
		Stack<String> stack = new SeqStack<String>(infix.length());
		StringBuffer postfix = new StringBuffer(infix.length()*2);
		
		int i = 0;
		while (i < infix.length()) {
			char ch = infix.charAt(i);
			switch (ch) {
				case '+': case '-':
					while (!stack.isEmpty() && !stack.peek().equals("("))
						postfix.append(stack.pop());
					stack.push(ch+"");
					i++;
					break;
				case '*': case '/':
					while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")))
						postfix.append(stack.pop());
					stack.push(ch+"");
					i++;
					break;
				case '(':
					stack.push(ch+"");
					i++;
					break;
				case ')':
					String out = stack.pop();
					while (out != null && !out.equals("(")) {
						postfix.append(out);
						out = stack.pop();
					}
					i++;
					break;
				default:
					while (i < infix.length() && ch >= '0' && ch <= '9') {
						postfix.append(ch);
						i++;
						if (i < infix.length())
							ch = infix.charAt(i);
					}
					postfix.append(" ");
				
			}
		}
		while (!stack.isEmpty())
			postfix.append(stack.pop());
		return postfix;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String infix = "11+22*(33-44)+55";
		System.out.println("infix: " + infix + " postfix: " + toPostfix(infix));
		System.out.println("infix: " + infix + " prefix: " + toPrefix(infix));
	}

}
