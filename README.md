# Damas
Diego Fernández Aceves - dfera10@gmail.com

## Índice
* [Modelo del dominio](#Modelo-del-dominio)
* [Requisitos](#Requisitos)
* [Vista de casos de uso](#Vista-de-casos-de-uso)
   * [Prototipo de interfaz](#Prototipo-de-interfaz)
   
## Modelo del dominio

[Wiki](https://en.wikipedia.org/wiki/Draughts)

[Youtube](https://www.youtube.com/watch?v=bN8VO8Nt0ws)

* Elementos: **dos jugadores**, un **tablero de ajedrez**, con una esquina blanca a la derecha de cada jugador, y **fichas blancas y negras**, para los dos jugadores, **12 peones y 2 damas**.

* En el **estado inicial** de la partida se colocan todas los peones de cada jugador en los **cuadros negros** de las **tres filas más cercanas a éste**, como en la siguiente imagen.

* Los **jugadores mueven alternativamente** una de sus fichas, **empezando** por el jugador de las fichas **blancas**, de una de las siguientes maneras:

    * un **peón de una casilla puede mover a una de las dos casillas adyacentes en diagonal y hacia adelante**, si ésta está **vacia**

    * un **peón de una casilla puede mover a una de las dos casillas adyacentes de las adyacentes en diagonal y hacia adelante**, si ésta esta **vacía** y la adyacente está **ocupada por un ficha contraria** repitiendo este mismo movimiento hasta 3 veces desde la nueva casilla. Todas las fichas contrarias "**saltadas**" en este momvimiento se **retiran del tablero**

    * en cualquiera de los dos casos anteriores, si un **peón termina su movimiento en la última fila del tablero**, se convierte en **dama**.

    * una **dama de una casilla puede mover a una de las casillas diagonales**, si existe como **máximo una ficha contraria**, repitiendo este mismo movimiento hasta 3 veces desde la nueva casilla. Todas las fichas contrarias "**saltadas**" se **retiran del tablero**

* **Pierde el jugador que no puede realizar movimientos**, porque **no se cumplen las condiciones** para el movimiento de todas sus fichas o que ya **no hay fichas** sobre el tablero

![Draughts](https://github.com/TheMercuryBeat/Draughts/blob/main/docs/images/draughtsModeloDominio.svg?raw=true)

### Requisitos
* Funcionalidad: Básica

* Interfaz: Texto

* Distribución: Standalone

* Persistencia: No

![DraughtsBoard](https://github.com/TheMercuryBeat/Draughts/blob/main/docs/images/draughts.jpg?raw=true)


## Vista de casos de uso
| Diagramas de Actores y Casos de Uso | Diagrama de Contexto           |
| ----------------------------------- | ------------------------------ |
| ![ActoresCasosUso](https://github.com/TheMercuryBeat/Draughts/blob/main/docs/images/diagramaActoresCasosUso.svg?raw=true) | ![Contexto](https://github.com/TheMercuryBeat/Draughts/blob/main/docs/images/diagtamaContexto.svg?raw=true) |

### Prototipo de interfaz

Arranque
```
Las Damas!!!

 12345678
1 n n n n1
2n n n n 2
3 n n n n3
4        4
5        5
6b b b b 6
7 b b b b7
8b b b b 8
 12345678
Mueven las blancas: 61.52

 12345678
1 n n n n1
2n n n n 2
3 n n n n3
4        4
5 b      5
6  b b b 6
7 b b b b7
8b b b b 8
 12345678
Mueven las negras: 32,43

 12345678
1 n n n n1
2n n n n 2
3   n n n3
4  n     4
5 b      5
6  b b b 6
7 b b b b7
8b b b b 8
 12345678
 Mueven las ...
```

Pierden las negras por bloqueo y continuan jugando
```
12345678
1        1
2        2
3        3
4        4
5        5
6        6
7 n      7
8b       8
 12345678
Derrota!!! No puedes mover tus fichas!!!
¿Queréis jugar otra? (s/n): s

Las Damas!!!

 12345678
1 n n n n1
2n n n n 2
3 n n n n3
4        4
5        5
6b b b b 6
7 b b b b7
8b b b b 8
 12345678
```

Peón convertido en dama
```
 12345678
1        1
2        2
3 n      3
4n b     4
5        5
6        6
7 b      7
8        8
 12345678
Mueven las negras: 72,83

 12345678
1        1
2        2
3 n      3
4n b     4
5        5
6        6
7        7
8  B     8
 12345678
 Mueven las ...
```

Posibles errores
```
Error!!! No te entiendo: <d><d>{,<d><d>}[0-2]
Error!!! No es una coordenada del tablero
Error!!! No hay ficha que mover
Error!!! No es una de tus fichas
Error!!! No vas en diagonal
Error!!! No está vacío el destino
Error!!! No comes contrarias
Error!!! No se puede comer tantas en un movimiento
Error!!! No avanzas
Error!!! No respetas la distancia
Error!!! No se puede comer tantas en un salto
```
Pierden las blancas sin fichas y no continuan jugando
```
12345678
1        1
2        2
3        3
4        4
5        5
6  n n   6
7 n      7
8  N     8
 12345678
Derrota!!! No puedes mover tus fichas!!!
¿Queréis jugar otra? (s/n): s
```