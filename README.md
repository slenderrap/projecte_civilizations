## Civilizations
Un proyecto de Luciano Poyanco, Mar Mèlich y Oriol Arribas.

## Sobre Civilizations
Civilizations es un juego de estrategia en el que deberás trabajar en tu civilización para protegerla de los ataques etenemigos. En base a los edificios de tu civilización, la generación de recursos será más o menos abundante. Estos recursos te permitirán comprar tropas para luchar cuando se acerque la batalla.

## Hecho en
Civilizations está programado en Java y cuenta con una base de datos PL/SQL.

## Cómo instalar
- Descarga el repositorio.
- Abre PL/SQL y ejecuta el siguente código en System para crear la sesión Civil:
```bash
alter session set "_ORACLE_SCRIPT"=true;
CREATE USER civil IDENTIFIED BY civil;
GRANT ALL PRIVILEGES TO civil;
COMMIT;
```
- En esta nueva sesión Civil ejectua el archivo bbdd.txt que se encuentra en la carpeta M02 para crear la base de datos del juego.
- Importa a Eclipse la carpeta M03 e inicia la clase Main.
