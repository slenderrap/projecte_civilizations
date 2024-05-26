## Civilizations
Un proyecto de Luciano Poyanco, Mar Mèlich y Oriol Arribas.

## Sobre Civilizations
Civilizations es un juego de estrategia en el que deberás trabajar en tu civilización para protegerla de los ataques etenemigos. En base a los edificios de tu civilización, la generación de recursos será más o menos abundante. Estos recursos te permitirán comprar tropas para luchar cuando se acerque la batalla.

## Hecho en
Civilizations está programado en Java y cuenta con una base de datos PL/SQL.

## Cómo instalar
- Descarga el repositorio.
- Abre PL/SQL y ejecuta el siguente código en "System" para crear la sesión "Civil":
```bash
alter session set "_ORACLE_SCRIPT"=true;
CREATE USER civil IDENTIFIED BY civil;
GRANT ALL PRIVILEGES TO civil;
COMMIT;
```
- Para crear la base de datos del juego, ejectua dentro de esta nueva sesión "Civil" el archivo bbdd.txt que se encuentra en la carpeta M02 del repositorio.
- Importa a Eclipse la carpeta M03, abre el archivo "Datos.java" que se encuentra en la carpeta "bbdd".
- Si usas una máquina virtual para la base de datos cambia a tu IP en la siguiente línea:
```bash
private String urlDatos = "jdbc:oracle:thin:@192.168.56.2:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false";
```
- Si usas en local la base de datos cambia a tu IP en la siguiente línea:
```bash
private String urlDatos = "jdbc:oracle:thin:@localhost:1521/xe?serverTimezone=UTC&autoReconnect=true&useSSL=false";
```


- Abre el archivo "Main.java" de la carpeta "Game" e inicia el archivo para empezar a jugar.
