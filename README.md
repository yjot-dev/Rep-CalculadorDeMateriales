# CALCULADORA DE MATERIALES (CdM)
CdM es una aplicación móvil diseñada para simplificar y agilizar la estimación de insumos en proyectos de construcción. Su objetivo principal es ofrecer una herramienta directa y eficiente que permita a los usuarios calcular la cantidad precisa de materiales necesarios para trabajos específicos, como la construcción de muros o la instalación de pisos.

# Características principales
- 🪟 Interfaz clasica con XML
- 📊 Integración con ViewModel + StateFlow
- 🎨 Patrón de diseño arquitectónico con MVVM
- 💉 Inyección de dependencias con Hilt
- 📱 Compatible con Android 7.0 (API 24) en adelante

# Instalación
- Clona el repositorio: git clone https://github.com/yjot-dev/Rep-CalculadorDeMateriales.git
- Abre el proyecto en Android Studio (Giraffe o superior)
- Sincroniza dependencias con Gradle
- Conecta un dispositivo o emulador y ejecuta la app

# Tecnologías usadas
- Kotlin
- XML
- AndroidX (Lifecycle, Core KTX)
- Material 3

# Uso
El flujo de uso de la aplicación ha sido diseñado para ser intuitivo y funcional, guiando al usuario a través de un proceso claro y sin complicaciones:

1. Navegación Principal: Al iniciar la aplicación, el usuario es recibido con un menú claro en la barra de navegación inferior. Este menú presenta tres opciones directas que definen las capacidades de la herramienta:
   - Pared: Permite calcular los materiales para la construcción de muros.
   - Piso: Facilita el cálculo para la instalación de baldosas.
   - Tema: Ofrece personalización de la interfaz.
2. Cálculo de Materiales para Paredes: En esta sección, el usuario puede estimar la cantidad de ladrillos o bloques requeridos. Para ello, debe ingresar las dimensiones de la pared (alto y largo) y seleccionar el tipo de material a utilizar (ladrillo o bloque). Tras ingresar los datos y presionar el botón de "Calcular", la aplicación muestra de inmediato el número exacto de unidades necesarias para completar el trabajo.
3. Cálculo de Materiales para Pisos: Si el objetivo es instalar un nuevo piso, esta sección optimiza el proceso. El usuario debe proporcionar el área total de la habitación (en metros cuadrados) y el área que cubre cada caja de baldosas. Al hacer clic en "Calcular", la aplicación determina la cantidad de cajas de baldosas que se necesitarán para cubrir toda la superficie.
4. Personalización de la Interfaz: Para mejorar la experiencia de usuario, la sección de "Tema" permite cambiar la apariencia visual de la aplicación. Con un simple clic, es posible alternar entre un tema claro y uno oscuro, adaptándose a las preferencias del usuario o a las condiciones de iluminación del entorno.

En resumen, CdM es una solución práctica y directa que responde a una necesidad clave en el sector de la construcción. Su diseño enfocado en la usabilidad permite a cualquier persona, independientemente de su experiencia técnica, obtener estimaciones rápidas y precisas, optimizando la planificación y la compra de materiales de manera efectiva.

# Ver video Demo
[Ver en Youtube](https://youtu.be/ZXCB118Kv2M)

# Contribución
- Haz un fork del repositorio
- Crea una rama con tu feature: git checkout -b feature/nueva-funcionalidad
- Haz commit de tus cambios: git commit -m "Agrega nueva funcionalidad"
- Haz push a la rama: git push origin feature/nueva-funcionalidad
- Abre un Pull Request

# Licencia
Este proyecto está bajo la licencia GPL-3.0. Consulta el archivo LICENSE para más detalles.