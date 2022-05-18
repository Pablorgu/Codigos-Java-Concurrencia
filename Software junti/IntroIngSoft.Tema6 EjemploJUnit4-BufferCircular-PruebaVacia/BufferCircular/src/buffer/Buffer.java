package buffer;

import java.util.ArrayList;
import java.util.List;


public class Buffer<T> {
	private List<T> lista = null;
	private int readIndex, writeIndex;
	private int nelem;
	
	
	public Buffer(int size) {
		lista = new ArrayList<T>(size);
		
		for (int i=0; i<size; i++)
			lista.add(null);
		
		nelem = 0;
		readIndex = writeIndex = 0;
	}
	
	public void put(T element) throws BufferLlenoException {
		if (isFull())
			throw new BufferLlenoException("Buffer lleno!!");
		
		lista.set(writeIndex, element);
		writeIndex = (writeIndex+1) % lista.size();
		nelem++;
	}
	
	public T get() throws BufferVacioException {
		T elem = null;
		
		if (isEmpty())
			throw new BufferVacioException("Buffer vacío!!");

		elem = lista.get(readIndex);
		readIndex = (readIndex+1) % lista.size();
		nelem--;
		
		return elem;
	}
	
	public T peek() throws BufferVacioException {
		T elem = null;
		
		if (isEmpty())
			throw new BufferVacioException("Buffer vacío!!");
		
		elem = lista.get(readIndex);
		return elem;
	}
	
	public boolean isFull() {
		return nelem >= lista.size();		
	}
	
	public boolean isEmpty() {
		return nelem == 0;		
	}
	
	public String toString() {
		String content = "Buffer = [";
		
		for (int i=0; i<nelem; i++)
			content += lista.get((readIndex+i) % lista.size()).toString() + "; ";
		
		return content;
	}
}
