Hola, este es un proyecto de EDAT. Gracias por leer. 

Desarrollar la clase Transporte de Agua con un menú de opciones para realizar las siguientes tareas:
1. Altas, bajas y modificaciones de ciudades  ✓
2. Altas, bajas y modificaciones de tuberías (1º versión: solo una tubería por par de ciudades en el mismo sentido) ✓
3. Alta de información de la cantidad de habitantes para año dado y ciudad dada ✓
4. Consultas sobre ciudades:
Dada una ciudad, mostrar  
- la cantidad de habitantes y 
- el volumen de agua que se habría distribuido en dicha ciudad en un año y mes determinado (mostrar ambos valores, por caudal y por cantidad de habitantes)
- Dadas dos subcadenas minNomb y maxNomb, y dos volúmenes de agua (minVol y maxVol) devolver todas las ciudades cuyo nombre esté en el rango [minNomb, maxNomb] que en un mes y año determinado hayan consumido un volumen de agua en el rango [minVol, maxVol].  


5. Consultas sobre transporte de agua: 
    Dada una ciudad A y una ciudad B:
- Obtener el camino que llegue de A a B tal que el caudal pleno del camino completo sea el mínimo entre los caminos posibles. El caudal pleno es el caudal definido por la tubería más pequeña del camino. Decir en qué estado se encuentra el camino.
    - El estado del camino se define en función del estado de todas sus tuberías.
    - Una tubería en reparación indica que todo el camino está en reparación. 
    - Una tubería en diseño indica que todo el camino está en diseño. 
    - Una tubería inactiva indica que todo el camino está inactivo. 
    - Si hay una tubería en diseño y otra en reparación o inactiva, el camino se considera en diseño.
    - Si hay una tubería en reparación y otra inactiva, el camino se considera inactivo.
- Obtener el camino que llegue de A a B pasando por la mínima cantidad de ciudades. Una vez obtenido el camino decir cuál es el estado.
6. Dado un año, generar y mostrar un listado de las ciudades ordenadas por consumo de agua anual de mayor a menor. 
- El consumo de agua anual se calcula como la sumatoria de los consumos mensuales de dicho año. 
- Utilice una estructura de datos auxiliar que considere apropiada para crear el listado que asegure la eficiencia al momento de la consulta. No debe guardar la estructura ni el listado después de mostrarlo. 
7. Mostrar sistema: es una operación de debugging que permite ver todas las estructuras utilizadas con su contenido (grafo, AVL y Mapeo) para verificar, en cualquier momento de la ejecución del sistema, que se encuentren cargadas correctamente. En este punto se deberá utilizar el método toString() para mostrar claramente cómo están almacenados los datos en la estructura, no es solo un listado. 
Por ejemplo, para el AVL debe indicarse cuál es la raíz y que para cada nodo se vea quién es su hijo izquierdo y hijo derecho, y la altura de cada nodo. En el Grafo, que se vean los vértices en el orden que estén almacenados y los arcos que lo tienen por origen.
