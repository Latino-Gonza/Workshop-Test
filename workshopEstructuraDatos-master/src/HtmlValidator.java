// Importamos las clases Queue y Stack de la librería java.util
import java.util.Queue;
import java.util.Stack;

// Definimos la clase HtmlValidator
public class HtmlValidator {
	// Definimos el método estático isValidHtml que recibe una cola de etiquetas HTML y devuelve una pila de etiquetas que no se han cerrado correctamente
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		// Creamos una pila vacía para almacenar las etiquetas de apertura
		Stack<HtmlTag> stack = new Stack<>();
		// Iteramos sobre cada etiqueta de la cola usando un bucle for-each
		for (HtmlTag tag : tags) {
			// Si la etiqueta es de auto-cierre, la ignoramos y continuamos con la siguiente iteración
			if (tag.isSelfClosing()) {
				continue;
			} else if (!tag.isOpenTag()) { // Si la etiqueta es de cierre, verificamos si coincide con la etiqueta en la cima de la pila
				if (!stack.isEmpty()) { // Si la pila no está vacía, comparamos las etiquetas usando el método matches
					if (stack.peek().matches(tag)) { // Si las etiquetas coinciden, eliminamos la etiqueta de la cima de la pila
						stack.pop();
					} else { // Si las etiquetas no coinciden, retornamos la pila actual como resultado
						return stack;
					}
				} else { // Si la pila está vacía y encontramos una etiqueta de cierre, retornamos null como resultado
					return null;
				}
			} else if (tag.isOpenTag()) { // Si la etiqueta es de apertura, la agregamos a la pila
				stack.push(tag);
			}
		}
		// Al final del bucle, retornamos la pila como resultado. Si la pila está vacía, significa que el HTML es válido. Si no, significa que hay etiquetas sin cerrar.
		return stack;
	}
}
