package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TClasificador {

	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
			case 1:
				return ordenarPorInsercion(datosParaClasificar);
			case 2:
				return ordenarPorShell(datosParaClasificar);
			case 3:
				return ordenarPorBurbuja(datosParaClasificar);
			case 4:
				return ordenarPorCountingSort(datosParaClasificar);
			case 5:
				return ordenarPorHeapSort(datosParaClasificar);
			case 6:
				return ordenarPorQuickSort(datosParaClasificar);
			case 7:
				return ordenarPorSelectionSort(datosParaClasificar);
			case 8:
				return ordenarPorBucketSort(datosParaClasificar);
			case 9:
				return ordenarPorRadixSort(datosParaClasificar);
			default:
				System.err.println("Este codigo no deberia haberse ejecutado");
				break;
		}
		return datosParaClasificar;
	}

	private void intercambiar(int[] vector, int pos1, int pos2) {
		int temp = vector[pos2];
		vector[pos2] = vector[pos1];
		vector[pos1] = temp;
	}

	// CORREGIDO FUNCIONANDO
	private int[] ordenarPorShell(int[] datosParaClasificar) {
		int j, inc;
		int[] incrementos = new int[] { 4, 2, 1 }; // Secuencia de incrementos

		//Recorre el array de incrementos.
		for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
			inc = incrementos[posIncrementoActual];

			for (int i = inc; i < datosParaClasificar.length; i++) {
				int clave = datosParaClasificar[i]; // Elemento actual a insertar que empieza por el incremento, en este caso 4 (4to elemento)
				j = i;

				while (j >= inc && datosParaClasificar[j - inc] > clave) {
					datosParaClasificar[j] = datosParaClasificar[j - inc];
					j -= inc;
				}

				datosParaClasificar[j] = clave;
			}
		}

		return datosParaClasificar;
	}

	// CORREGIDO FUNCIONANDO
	private int[] ordenarPorInsercion(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			for (int i = 1; i < datosParaClasificar.length; i++) {
				int valorActual = datosParaClasificar[i];
				int j = i - 1;
				while ((j >= 0) && (datosParaClasificar[j] > valorActual)) {
					intercambiar(datosParaClasificar, j+1, j);
					j--;
				}
				datosParaClasificar[j+1] = valorActual;
			}
			return datosParaClasificar;
		}
		return null;
	}

	// CORREGIDO FUNCIONANDO
	private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			int n = datosParaClasificar.length - 1;
			for (int i = 0; i <= n; i++) {
				for (int j = n; j >= (i + 1); j--) {
					if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
						intercambiar(datosParaClasificar, j - 1, j);
					}
				}
			}
			return datosParaClasificar;
		}
		return null;
	}

	private int[] ordenarPorBurbujaOptimizado(int[] datosParaClasificar) {
		if (datosParaClasificar == null) {
			return null; // Caso base: arreglo nulo
		}

		int n = datosParaClasificar.length - 1; // Último índice del arreglo
		boolean swapped;

		for (int i = 0; i <= n; i++) {
			swapped = false; // Reinicia la bandera en cada pasada

			// Recorre desde el final hasta la posición i
			for (int j = n; j > i; j--) {
				if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
					intercambiar(datosParaClasificar, j - 1, j);
					swapped = true; // Hubo un intercambio
				}
			}

			// Si no hubo intercambios, el arreglo ya está ordenado
			if (!swapped) {
				break;
			}
		}

		return datosParaClasificar;
	}

	public static boolean estaOrdenado(int[] datos) {
		for (int i = 0; i < datos.length - 1; i++) {
			if (datos[i] > datos[i + 1]) {
				return false; // Si un elemento es mayor que el siguiente, no está ordenado
			}
		}
		return true; // Si recorremos todo el arreglo sin encontrar problemas, está ordenado.
	}

	/**
	 * Método que implementa el algoritmo de Counting Sort.
	 * Este algoritmo organiza elementos en un rango conocido utilizando un arreglo auxiliar.
	 * Funciona para ordenar números enteros en un rango acotado.
	 *
	 * @param datos El arreglo de elementos a ordenar.
	 * @return Un nuevo arreglo ordenado.
	 */
	private int[] ordenarPorCountingSort(int[] datos) {
		// Validar entrada
		if (datos == null || datos.length == 0) {
			throw new IllegalArgumentException("El arreglo no puede estar vacío.");
		}

		// Determinar el rango mínimo y máximo
		int rangoMinimo = datos[0];
		int rangoMaximo = datos[0];
		for (int i = 1; i < datos.length; i++) {
			if (datos[i] < rangoMinimo) {
				rangoMinimo = datos[i];
			} else if (datos[i] > rangoMaximo) {
				rangoMaximo = datos[i];
			}
		}

		// Paso 1: Crear el arreglo auxiliar para contar ocurrencias
		int rango = rangoMaximo - rangoMinimo + 1; // Determinar el tamaño del rango
		int[] cuenta = new int[rango]; // Inicializar el arreglo de conteo

		// Contar las ocurrencias de cada elemento en el rango
		for (int i = 0; i < datos.length; i++) {
			cuenta[datos[i] - rangoMinimo]++;
		}

		// Paso 2: Acumular las cuentas para determinar posiciones finales
		for (int i = 1; i < cuenta.length; i++) {
			cuenta[i] += cuenta[i - 1];
		}

		// Paso 3: Crear el arreglo de salida y ubicar elementos en orden
		int[] resultado = new int[datos.length]; // Arreglo para los datos ordenados
		for (int i = datos.length - 1; i >= 0; i--) { // Recorremos de derecha a izquierda
			int valor = datos[i]; // Valor actual del dato
			resultado[cuenta[valor - rangoMinimo] - 1] = valor; // Ubicar en el lugar correspondiente
			cuenta[valor - rangoMinimo]--; // Reducir la cuenta disponible
		}

		return resultado; // Retornar el arreglo ordenado
	}

	protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
		// Construcción inicial del heap (heapify completo)
		for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) {
			armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
		}
		// Proceso de ordenamiento
		for (int i = datosParaClasificar.length - 1; i > 0; i--) {
			intercambiar(datosParaClasificar, 0, i); // Mover el mayor al final
			armaHeap(datosParaClasificar, 0, i - 1); // Reconstruir el heap para el rango restante
		}
		return datosParaClasificar;
	}

	private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
		int r = primero;
		while (r * 2 + 1 <= ultimo) { // Mientras tenga al menos un hijo izquierdo
			int hijoIzquierdo = 2 * r + 1;
			int hijoDerecho = hijoIzquierdo + 1;
			int posicionIntercambio = hijoIzquierdo;

			// Si hay hijo derecho, compara para encontrar el mayor
			if (hijoDerecho <= ultimo && datosParaClasificar[hijoDerecho] > datosParaClasificar[hijoIzquierdo]) {
				posicionIntercambio = hijoDerecho;
			}

			// Si el nodo actual es menor que el mayor hijo, intercambiar
			if (datosParaClasificar[r] < datosParaClasificar[posicionIntercambio]) {
				intercambiar(datosParaClasificar, r, posicionIntercambio);
				r = posicionIntercambio; // Continuar bajando en el heap
			} else {
				break; // Nodo en posición correcta
			}
		}
	}

	private int[] ordenarPorQuickSort(int[] datosParaClasificar) {
		if (datosParaClasificar == null || datosParaClasificar.length == 0) {
			return datosParaClasificar; // Si el array está vacío o es nulo, no hay nada que ordenar
		}
		quickSort(datosParaClasificar, 0, datosParaClasificar.length - 1);
		return datosParaClasificar;
	}

	private void quickSort(int[] array, int inicio, int fin) {
		if (inicio < fin) {
			int indicePivote = particion(array, inicio, fin);
			quickSort(array, inicio, indicePivote - 1); // Ordenar la sublista izquierda
			quickSort(array, indicePivote + 1, fin);   // Ordenar la sublista derecha
		}
	}

	private int particion(int[] array, int inicio, int fin) {
		// Elegir el pivote como el más grande de los dos primeros elementos
		if (array[inicio] > array[fin]) {
			intercambiar(array, inicio, fin); // Intercambiamos para que el mayor quede al final
		}
		int pivote = array[fin]; // Ahora el pivote es el mayor de los dos primeros elementos

		int i = inicio - 1;
		for (int j = inicio; j < fin; j++) {
			if (array[j] <= pivote) {
				i++;
				intercambiar(array, i, j); // Intercambiamos los elementos para colocar los menores a la izquierda
			}
		}
		intercambiar(array, i + 1, fin); // Colocamos el pivote en su posición final
		return i + 1;
	}

	private int[] ordenarPorSelectionSort(int[] datosParaClasificar) {
		if (datosParaClasificar == null || datosParaClasificar.length == 0) {
			return datosParaClasificar; // Si el array está vacío o es nulo, no hay nada que ordenar
		}

		// Implementación del Selection Sort
		for (int i = 0; i < datosParaClasificar.length - 1; i++) {
			int indiceMinimo = i; // Suponemos que el elemento actual es el mínimo

			// Busca el índice del valor más pequeño en el resto del array
			for (int j = i + 1; j < datosParaClasificar.length; j++) {
				if (datosParaClasificar[j] < datosParaClasificar[indiceMinimo]) {
					indiceMinimo = j; // Si encontramos un valor menor, actualizamos el índice del mínimo
				}
			}

			// Intercambiamos el valor mínimo encontrado con el valor en la posición i
			if (indiceMinimo != i) {
				intercambiar(datosParaClasificar, i, indiceMinimo);
			}
		}

		return datosParaClasificar;
	}

	private int[] ordenarPorRadixSort(int[] datosParaClasificar) {
		if (datosParaClasificar == null || datosParaClasificar.length == 0) {
			return datosParaClasificar; // Si el array está vacío o es nulo, no hay nada que ordenar
		}

		// Encontrar el valor máximo para determinar cuántas pasadas se necesitan
		int max = obtenerMaximo(datosParaClasificar);

		// Realizamos el ordenamiento basado en cada dígito (de menor a mayor)
		for (int exp = 1; max / exp > 0; exp *= 10) {
			// Usamos Counting Sort para ordenar por el dígito correspondiente (exp)
			datosParaClasificar = countingSortRadix(datosParaClasificar, exp);
		}

		return datosParaClasificar;
	}

	// Función para obtener el máximo valor del array
	private int obtenerMaximo(int[] array) {
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	// Función de Counting Sort, que ordena según el dígito en la posición exp
	private int[] countingSortRadix(int[] array, int exp) {
		int[] output = new int[array.length];
		int[] count = new int[10]; // Para contar los dígitos (0-9)

		// Contamos las ocurrencias de cada dígito en la posición exp
		for (int i = 0; i < array.length; i++) {
			count[(array[i] / exp) % 10]++;
		}

		// Modificamos el array count para almacenar la posición acumulada
		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		// Construimos el array output ordenado según el dígito en la posición exp
		for (int i = array.length - 1; i >= 0; i--) {
			int digit = (array[i] / exp) % 10;
			output[count[digit] - 1] = array[i];
			count[digit]--;
		}

		// Copiamos el array output al array original para la próxima pasada
		for (int i = 0; i < array.length; i++) {
			array[i] = output[i];
		}

		return array;
	}

	public int[] ordenarPorBucketSort(int[] datosParaClasificar) {
		if (datosParaClasificar == null || datosParaClasificar.length == 0) {
			return datosParaClasificar;
		}

		// Encontramos el valor máximo para determinar la cantidad de "buckets"
		int max = Arrays.stream(datosParaClasificar).max().getAsInt();
		int numBuckets = datosParaClasificar.length; // Número de cubos igual al número de elementos

		// Crear los cubos
		List<Integer>[] buckets = new List[numBuckets];
		for (int i = 0; i < numBuckets; i++) {
			buckets[i] = new ArrayList<>();
		}

		// Distribuir los elementos en los cubos
		for (int num : datosParaClasificar) {
			int index = (num * numBuckets) / (max + 1); // Función de dispersión
			buckets[index].add(num);
		}

		// Ordenar cada cubo y recolectar los elementos
		int index = 0;
		for (List<Integer> bucket : buckets) {
			Collections.sort(bucket);  // Usamos Insertion Sort o cualquier otro algoritmo
			for (int num : bucket) {
				datosParaClasificar[index++] = num;
			}
		}

		return datosParaClasificar;
	}
}