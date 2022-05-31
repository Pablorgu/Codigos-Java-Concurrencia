package juego;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class Mesa {
    //0 - piedra, 1 - papel, 2 - tijeras

	//ArrayList<Integer> list = new ArrayList<>();
	private int jugador0;
	private int jugador1;
	private int jugador2;
	private int njugadores;
	private int resultado; //-1 empate, si no marco la eleccion ganadora
	private ReentrantLock l = new ReentrantLock();
	private Condition okTodos = new l.newCondition();
	;
	/**
	 * 
	 * @param jug jugador que llama al m�todo (0,1,2)
	 * @param juego jugada del jugador (0-piedra,1-papel, 2-tijeras)
	 * @return  si ha habido un ganador en esta jugada se devuelve 
	 *          la jugada ganadora
	 *         o -1, si no ha habido ganador
	 * @throws InterruptedException
	 * 
	 * El jugador que llama a este m�todo muestra su jugada, y espera a que 
	 * est�n la de los otros dos. 
	 * Hay dos condiciones de sincronizaci�n
	 * CS1- Un jugador espera en el m�todo hasta que est�n las tres jugadas
	 * CS2- Un jugador tiene que esperar a que finalice la jugada anterior para
	 *     empezar la siguiente
	 * 
	 */
	public int nuevaJugada(int jug,int juego) throws InterruptedException{
		++njugadores;
		System.out.println("Se ha unido el jugador "+ jug);
		switch (jug) {
			case 0:
				int jugador0 = juego;
				break;
			case 1:
				int jugador1 = juego;
				break;
			case 2:
				int jugador2 = juego;
				break;
		}
		if(njugadores==3) {
			okTodos.notifyAll();
		}
		while(njugadores<3) {
			okTodos.await();
		}
		if (jugador0 == jugador1 && jugador1 == jugador2){
			resultado =-1;
		} else if(jugador0 == jugador1) {
			
		} else if (jugador1== jugador2) {
			
		} else if (jugador2 == jugador0) {

		} else{ 
			resultado = 1;
		}
		return 0;
	}
}
