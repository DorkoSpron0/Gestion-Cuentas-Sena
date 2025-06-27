# MisMovs

## Descripción
**MisMovs** es una aplicación desarrollada en Spring Boot y Android con su integración Jetpack Compose para gestionar transacciones bancarias de manera sencilla. Permite realizar operaciones CRUD sobre registros de transacciones, almacenando información como el tipo de transacción, descripción, monto y tipo de cuenta.

Está diseñada para ejecutarse localmente o conectarse a servicios en la nube, facilitando el acceso seguro y organizado a los datos.

Incluye Gradle Wrapper para garantizar que cualquier usuario pueda compilar y ejecutar el proyecto sin necesidad de tener Gradle instalado previamente.

## Características principales
- Registrar un nuevo usuario, iniciar sesión
- Crear, consultar, actualizar y eliminar transacciones bancarias.

- Soporte para diferentes tipos de transacción (ingreso, egreso) y cuentas (bancaria, efectivo, QR, virtual).

- Posibilidad de ejecución local o integración con servicios cloud.

- Proyecto gestionado con Gradle Wrapper para fácil instalación y ejecución.

## Requisitos
- Contar con Java instalado en su versión **21**.
- Contar con el puerto *8081* libre.
- Tener instalado PostgreSQL.
- Contar con conexión a internet para la descarga de las dependencias.

## Paso a paso
### Base de datos
Para la configuración de base de datos solo es necesario contar con la base de datos llamada **gestionBancariaSena**
```SQL
CREATE DATABASE IF NOT EXISTS gestionBancariaSena;
```
Y ya con esto bastará.

### API REST Spring Boot
- Clonar el repositorio y entrar a la carpeta *gestionCuentasBancariasSena*
```bash
git clone https://github.com/DorkoSpron0/Gestion-Cuentas-Sena.git
cd Gestion-Cuentas-Sena
cd gestionCuentasBancariasSena
```

- Ejecutar el siguiente comando en **POWERSHELL** en caso de windows:
```bash
$env:SPRING_DATASOURCE_USERNAME = "postgres"; $env:SPRING_DATASOURCE_PASSWORD = "root"; .\gradlew bootRun
```
Y en caso de estar en Linux ejecutar el siguiente comando en Terminal:
```bash
SPRING_DATASOURCE_USERNAME=postgres SPRING_DATASOURCE_PASSWORD=root ./gradlew bootRun
```
> Recuerda cambiar los valores *postgres* por tus credenciales de acceso

### Aplicación Android
Para la aplicación de android solo abre el proyecto en tu IDE favorito *(Preferiblemente Android Studio)* seleccionar algún emulador de android y darle a ejecutar y listo.
