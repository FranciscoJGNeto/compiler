package br.com.controller;

@SuppressWarnings("unchecked")
public class StackEscopo<T> {

	private T[] stack = (T[]) new Object[5];
	private int index = 0;
	
	public void push(T element) {
		if (index > stack.length) {
			T[] newStack = (T[]) new Object[stack.length + 5];
			for (int i = 0; i < stack.length; i++) {
				newStack[i] = stack[i];
			}
			
			stack = newStack;
		}
		
		stack[index] = element;
		index++;
	}
	
	public T pop() {
		T pop = currentElement();
		stack[index] = null;
		index--;
		
		return pop;
	}
	
	public T currentElement() {
		return stack[index-1];
	}
	
	public int size() {
		return index;
	}
	
}
