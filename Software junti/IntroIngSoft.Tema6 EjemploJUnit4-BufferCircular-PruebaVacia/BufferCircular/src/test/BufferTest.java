package test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import buffer.Buffer;
import buffer.BufferVacioException;
import buffer.BufferLlenoException;

public class BufferTest {
	private static final int CAPACIDAD = 3;
	private Buffer<Integer> buf;
	
}
