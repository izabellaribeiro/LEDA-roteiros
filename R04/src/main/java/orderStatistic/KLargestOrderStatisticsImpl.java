package orderStatistic;

import util.Util;

import java.util.Arrays;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {

		T[] eo = (T[]) new Comparable[0]; ;

		if (k >= 1 && array != null && k <= array.length){

			eo = (T[]) new Comparable[k];

			for (int i = 1; i <= k; i++) {
				eo[i - 1] = orderStatistics(array, i);
			}
		}

		return eo;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista
	 * a k-esima estatistica de ordem, entao retorna null.
	 *
	 * Obs: o valor de k deve ser entre 1 e N.
	 *
	 * @param array
	 * @param k
	 * @return
	 */

	public T orderStatistics(T[] array, int k){

		if (k >= 1 && k <= array.length) {
			for (int i = 0; i < array.length; i++) {

				int smaller = i;

				for (int j = i + 1; j < array.length; j++) {
					if (array[j].compareTo(array[smaller]) > 0) {
						smaller = j;
					}
				}

				Util.swap(array, i, smaller);

				if (k == i + 1) {
					return array[i];
				}
			}
		}
		return null;
	}
}
