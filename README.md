# GeniusKids

GeniusKids, es un proyecto de una aplicación educativa que ofrece contenido interactivo en las cuatro materias básicas, donde los estudiantes podrán acceder a una amplia gama de contenidos, minijuegos y evaluaciones, adaptadas a diferentes niveles de dificultad, desde lo más básico hasta lo más avanzado en cada materia.

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Deployment** para conocer como desplegar el proyecto.


### Pre-requisitos 📋

_Que cosas necesitas para instalar el software y como instalarlas_

```
Para Desarrolladores
1- Android Studio
        * Descarga e instala Android Studio desde su sitio oficial.
        * Configura el SDK de Android:
                ° Asegúrate de instalar el SDK de la versión mínima requerida por la app (especificado en build.gradle).
                ° Añade herramientas necesarias como Android Emulator y SDK Platform Tools.
2- Java Development Kit (JDK)
        * La app requiere JDK 11 o superior.
        * Descárgalo desde Oracle o usa una versión OpenJDK (como AdoptOpenJDK).

3- Dispositivo o Emulador Android
        * Un dispositivo físico con depuración USB habilitada o un emulador configurado en Android Studio.
        * La versión de Android debe cumplir con el nivel mínimo de API de la aplicación.

Solicita las claves API o archivos de configuración necesarios, como google-services.json.
Asegúrarse de configurar el proyecto correctamente en el panel correspondiente (El caso, en Firebase Console).
```

### Instalación 🔧
```
1- Dispositivo Android
       * Asegúrate de que el dispositivo cumple con:
              ° Versión mínima de Android: Android 8.0 (API 26) o superior.
              ° Espacio de almacenamiento suficiente para la app y sus datos.

2- APK o Acceso al Play Store
       * Solicita el archivo .apk al desarrollador.
```

### Clonar repositorio en Android Studio
_Pre-requisitos
```
1- Android Studio instalado.
2- Git instalado en tu sistema.
```
Asegúrate de configurarlo con tu usuario y correo:
```sh
git config --global user.name "Tu Nombre"
git config --global user.email "tuemail@example.com"
```
Y por ultimo.
```
3- URL del repositorio a clonar.
       o https://github.com/Ricardo1703-svg/GeniusKids_ING_SOFTWARE.git
```

 _Pasos para clonar un repositorio:
```
       1.Abre Android Studio.
              o Si estás en la pantalla de bienvenida, selecciona "Get from VCS" (Obtener del control de versiones).
              o Si ya tienes un proyecto abierto, ve a File > New > Project from Version Control.

       2.Selecciona el sistema de control de versiones:
              o En la ventana que aparece, selecciona Git.

       3.Introduce la URL del repositorio:
              o En el campo de texto, pega la URL del repositorio que deseas clonar (por ejemplo el de nuestro proyecto: https://github.com/Ricardo1703-svg/GeniusKids_ING_SOFTWARE.git).

       4.Selecciona la ruta de destino:
              o Escoge la carpeta en tu computadora donde se clonará el proyecto.

       5. Inicia el clonado:
              o Haz clic en el botón Clone.

       7.Espera a que se descargue el proyecto:
              o Android Studio descargará todos los archivos del repositorio y configurará el proyecto automáticamente.
```

## Ejecutando las pruebas ⚙️

Ejecutamos las pruebas teniendo en el build.gradle el JUnit o Mockito y usa el siguiente comando:
```
./gradlew testDebugUnitTest
```

### Analice las pruebas end-to-end 🔩
Ingresar con cuentas diferentes de Google y luego de ingresar mantiene la sesion del usuario abierta		

```
Entrar sin nigun problema		                                                               Como se esperaba		
Al cerrar la app el estado de la cuenta se guarda y no se requiere loguearse denuevo		Como se esperaba		
```

### Y las pruebas de estilo de codificación ⌨️
Para personalizar las reglas, puedes generar un archivo de configuración:
```
./gradlew detektGenerateConfig
```

Para analizar tu proyecto con Detekt:
```
./gradlew detekt
```

## Construido con 🛠️

* [Android Studio for Windosws](https://developer.android.com/studio?hl=es-419) - El Entorno de trabajo usado
* [Firebase](https://console.firebase.google.com/u/0/) - Manejador de Api para logueo y administrador de la base de datos

## Autores ✒️

* **Jeison Baldomar Ventura Sorto** - *Scrum Master - Lider* [Baldomar](https://github.com/Baldomar)
* **Ricardo Alexander Alvarez Portillo** - *Scrum Team - Backend* [RichDev](https://github.com/Ricardo1703-svg)
* **Edvin Abelardo Guevara Vasquez** - *Scrum Team - Product owner* [Edvin0001001](https://github.com/Edvin0001001)
* **Karla Lisseth Lopez Herrera** - *Scrum Team - Frontend/Tester*

