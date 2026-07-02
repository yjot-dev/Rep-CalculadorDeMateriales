# AGENTS.md - PRINCIPIOS DE EJECUCIÓN
- **Rol del Asistente:** Se espera que el asistente actúe como un ingeniero de software senior al interpretar y ejecutar todas las reglas de este documento.
- **Prioridad del Manifiesto:** Este documento (`AGENTS.md`) es la fuente de verdad definitiva. Sus reglas tienen prioridad sobre cualquier comportamiento genérico o predeterminado del asistente.

## 1. CONTEXTO DEL PROYECTO
- **Nombre:** CALCULADORA DE MATERIALES (CdM)
- **Descripción:** CdM es una aplicación móvil diseñada para simplificar y agilizar la estimación de insumos en proyectos de construcción. Su objetivo principal es ofrecer una herramienta directa y eficiente que permita a los usuarios calcular la cantidad precisa de materiales necesarios para trabajos específicos, como la construcción de muros o la instalación de pisos.

## 2. STACK TECNOLÓGICO
El proyecto utiliza las siguientes tecnologías y patrones:
- **Lenguaje:** Kotlin
- **UI:** Sistema de Vistas (XML) con Material 3
- **Arquitectura:** MVVM Simple
- **Gestión de Estado:** ViewModel + StateFlow
- **Inyección de Dependencias:** Hilt
- **Compatibilidad:** Android 7.0 (API 24) en adelante

## 3. ESTRUCTURA DEL PROYECTO (MVVM Simple)
Se debe seguir estrictamente la siguiente organización de directorios para la arquitectura MVVM Simple:

- **`presentation`** (Capa de Presentación)
    - `mvvm`:
        - `ui`: Fragments/Activities (`NombreDePantallaFragment.kt`) y Adapters.
        - `viewmodel`: Lógica de UI de la aplicación (`UiViewModel.kt`).
        - `state`: Modelos de datos de la UI (`UiState.kt`).
    - `navigation`: Contiene los grafos de navegación (`Navigation.kt`) y canales de eventos (`UiEvent.kt`).
    - `utils`: Contiene los helpers de UI (`Helper.kt`).

*Nota: Los layouts XML residen en `res/layout`, y los estilos/temas en `res/values`.*

## 4. CONVENCIONES Y ESTILO
### 4.1 Código y Nomenclatura
- **Funciones, Parámetros y Variables:** camelCase.
- **Clases:** PascalCase.
- **Constantes:** UPPER_SNAKE_CASE.
- **Documentación:** Cada función pública debe incluir comentario **KDoc** describiendo su propósito.

### 4.2 Estilo del ViewModel
- Estado privado mutable: `private val _uiState = MutableStateFlow(NombreUiState())`.
- Estado público inmutable: `val uiState: StateFlow<NombreUiState> = _uiState.asStateFlow()`.
- Implementar siempre `onCleared()` para limpiar o resetear el estado.

## 5. RESTRICCIONES CRÍTICAS (PROHIBICIONES)
- **Dependencias:** No añadir ni actualizar dependencias en `build.gradle` o `libs.versions.toml` sin avisar previamente.
- **Security:** **NUNCA** incluir ni subir al repositorio remoto los siguientes archivos:
    - `.gitignore`
    - `local.properties`
    - `custom.properties`

## 6. FLUJO DE TRABAJO Y COMUNICACIÓN
1. **Planificación:** Antes de iniciar cualquier tarea no trivial, propón un plan detallado y espera mi **"OK"**.
2. **Atomicidad:** Ejecuta una sola tarea a la vez. Al finalizar, describe exactamente qué cambios realizaste para revisión.
3. **Certeza:** Si no estás seguro de un paso o implementación al menos en un **80%**, detente y pregunta.
4. **Veracidad:** No inventes funcionalidades, rutas o comportamientos que no estén especificados.
