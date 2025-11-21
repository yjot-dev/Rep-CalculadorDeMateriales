# APP LOGIN
Esta app permite calcular el número de ladrillos necesarios para construir una 
pared y el número de baldosas necesarias para instalarlas en el piso, si se está 
en la opción pared se solicita ingresar sus dimensiones (alto y largo), así como 
el tipo de ladrillo o bloque a usar.

# Características principales
- 🪟 Interfaz clasica con XML
- 📊 Integración con ViewModel + StateFlow
- 🎨 Patrón de diseño arquitectónico con MVVM
- 🧩 Inyección de dependencias con Hilt
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
- Al abrir la app, se muestra un Menu en la barra inferior con 3 opciones: Pared, Piso, Tema.
- En Pared, el usuario debe ingresar la altura y el largo de la pared, así como el
tipo de ladrillo o bloque a usar, luego da click en calcular y le muestra el resultado.
- En Piso, el usuario debe ingresar el área de la habitación y el área de cada caja
de baldosas, luego da click en calcular y le muestra el resultado.
- En Tema, el usuario al hacer click en el botón, puede cambiar el tema a claro o oscuro.

# Ver video Demo
No disponible aun

# Contribución
- Haz un fork del repositorio
- Crea una rama con tu feature: git checkout -b feature/nueva-funcionalidad
- Haz commit de tus cambios: git commit -m "Agrega nueva funcionalidad"
- Haz push a la rama: git push origin feature/nueva-funcionalidad
- Abre un Pull Request

# Licencia
Este proyecto está bajo la licencia GPL-3.0. Consulta el archivo LICENSE para más detalles.