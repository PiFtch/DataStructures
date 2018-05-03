package ch3;
import ch2.SinglyList;
import ch2.SinglyNode;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CountWord {
	
	public static void main(String[] args) throws IOException {
		FileReader reader = null;
		try {
			File file = new File("file.txt");
			if (!file.exists()) {
				System.out.println("file.txt does not exist!");
			}
			reader = new FileReader(file);
			char[] buffer = new char[1024];
			int i = reader.read(buffer);
			
			MyString context = new MyString(buffer, 0, i);
			System.out.println(context.toString() + " " + context.length());
			
			WordList Words = new WordList();
			int begin = 0;
			while (begin < context.length()) {
				char ch = context.charAt(begin);
				if (ch == ' ' || ch == ',' || ch == '.' || ch == '\n' || ch == '\r') {
					begin++;
				} else {
					int end = begin;
					while (end < context.length()) {
						char chend = context.charAt(end);
						if (chend == ' ' || chend == ',' || chend == '.' || chend == '\r' || chend == '\n') {
							break;
						} else {
							end++;
						}
					}
					MyString word = new MyString(context.substring(begin, end));
					System.out.print(word.toString() + " ");
					System.out.println(end);
					begin = end+1;
					Words.count(word);
				}
			}
			System.out.println(Words.toString());
		} catch(IOException ex) {
			System.out.println("exception");
		} 
	}
}

class WordNode {
	MyString word;
	int count;
	WordNode next;
	
	public WordNode() {
		this.word = null;
		this.next = null;
		this.count = 0;
	}
	
	public WordNode(MyString word) {
		this.word = word;
		this.next = null;
		this.count = 1;
	}
	
	public String toString() {
		return (this.word.toString() + "   " + this.count);
	}

}

class WordList {
	WordNode head;
	
	public WordList() {
		this.head = new WordNode();
	}
	
	public void add(MyString word) {
		WordNode p = this.head;
		while (p.next != null)
			p = p.next;
		p.next = new WordNode(word);
	}
	
	public void count(MyString word) {
		WordNode p = this.head;
		while (p.next != null) {
			if (p.next.word.equals(word)) {
				p.next.count++;
				break;
			}
			else
				p = p.next;
		}
		if (p.next == null)
			this.add(word);
	}
	
	public String toString() {
		String str = "";
		WordNode p = this.head;
		int i = 0;
		while (p.next != null) {
			str += p.next.toString() + " ";
			i++;
			if (i % 6 == 0)
				str += "\n";
			p = p.next;
		}
		return str;
	}
}