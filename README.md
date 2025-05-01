# WalletMate



**WalletMate** es una aplicación de control financiero desarrollada con **Android Kotlin**, **XML** y arquitectura **MVVM**. Esta app ayuda a los usuarios a gestionar sus finanzas, realizar un seguimiento de ingresos y gastos, y visualizar sus datos financieros mediante gráficos interactivos.

---

## 📸 Capturas de Pantalla

| Pantalla de Inicio | Inicio de Sesión | Registro |
|--------------------|------------------|----------|
| <img src="https://i.postimg.cc/4ds1Jjdj/imagen-2025-05-01-181651114.png" width="250"/> | <img src="https://i.postimg.cc/KjVLgWGG/imagen-2025-05-01-181748055.png" width="250"/> | <img src="https://i.postimg.cc/rm7xDbF8/imagen-2025-05-01-182555957.png" width="250"/> |

| Pantalla Principal | Transacciones | Perfil |
|--------------------|---------------|--------|
| <img src="https://i.postimg.cc/SsPjZzwV/imagen-2025-05-01-182117129.png" width="250"/> | <img src="https://i.postimg.cc/8P6f0tkv/imagen-2025-05-01-181925927.png" width="250"/> | <img src="https://i.postimg.cc/kMVy7NXJ/imagen-2025-05-01-182151070.png" width="250"/> |

| Gráfico de Ingresos/Gastos | Detalles de Transacción | Actualización de Moneda en Perfil |
|----------------------------|--------------------------|-----------------------------------|
| <img src="https://i.postimg.cc/vBx6wz7C/imagen-2025-05-01-183059382.png" width="250"/> | <img src="https://i.postimg.cc/fy0y1Rss/imagen-2025-05-01-182133866.png" width="250"/> | <img src="https://i.postimg.cc/FHPffqD2/imagen-2025-05-01-183228176.png" width="250"/> |

---

## 📱 Funcionalidades

### **1. Pantalla de Inicio**
- Pantalla de bienvenida atractiva con animaciones.

### **2. Autenticación**
- **Inicio de Sesión y Registro**: Autenticación segura para gestionar presupuestos personales.

### **3. Pantalla Principal**
- **Resumen del Presupuesto**: Muestra el presupuesto total, ingresos y gastos.
- **Desglose de Ingresos/Gastos**: Gráfico circular interactivo para visualizar los datos financieros.

### **4. Transacciones**
- **Agrupadas por Fecha**: Visualiza todas las transacciones organizadas por fecha para un seguimiento sencillo.

### **5. Gestión de Perfil**
- **Actualizar Perfil**: Los usuarios pueden modificar su información personal.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguajes de Programación**: Kotlin, XML  
- **Arquitectura**: MVVM (Modelo-Vista-ViewModel)  
- **Base de Datos**: Firebase Firestore  
- **Gráficos**: MPAndroidChart  
- **Autenticación**: Firebase Authentication  

---

## 🚀 Primeros Pasos

### **Requisitos Previos**
- Android Studio (última versión)
- Plugin de Kotlin instalado
- Dispositivo/emulador Android con API nivel 21 o superior

### **Instalación**
1. Clona el repositorio:
   ```bash
   git clone https://github.com/StivenAC/WalletMate.git
   ```
2. Abre el proyecto en Android Studio.  
3. Compila y ejecuta la app en tu dispositivo o emulador.

---

## 🧩 Estructura del Proyecto

```
WalletMate/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/talhaatif/financeapk/
│   │   │   │   ├── adapters/          # Adaptadores para RecyclerView
│   │   │   │   ├── dialog/            # Diálogos personalizados
│   │   │   │   ├── firebase/          # Clases relacionadas con Firebase
│   │   │   │   ├── models/            # Modelos de datos
│   │   │   │   ├── repositories/      # Clases de repositorio
│   │   │   │   ├── viewmodel/         # Clases ViewModel
│   │   │   ├── res/
│   │   │   │   ├── drawable/          # Imágenes e íconos
│   │   │   │   ├── color/             # Recursos de color
│   │   │   │   ├── font/              # Tipografías personalizadas
│   │   │   │   ├── layout/            # Diseños XML
│   │   │   │   ├── menu/              # Recursos de menú
│   │   │   │   ├── mipmap/            # Íconos de lanzador
│   │   │   │   ├── anim/              # Archivos de animación
│   │   │   │   ├── values/            # Colores, cadenas y estilos
│   ├── build.gradle                   # Archivo Gradle a nivel de app
├── build.gradle                       # Archivo Gradle a nivel de proyecto
```

---


## 🗂️ Estructura del Proyecto
La aplicación está organizada siguiendo la arquitectura MVVM, con una separación clara entre la lógica, la interfaz y los datos:

- model/ → Clases de datos, como Transaction.

- viewmodel/ → Lógica de presentación y conexión con la base de datos (por ejemplo, TransactionViewModel).

- adapter/ → Adaptadores para RecyclerView que muestran las transacciones.

- ui/ o Fragments → Componentes visuales como TransactionsListFragment.

- utils/ → Utilidades como PDFExporter para generar resúmenes en PDF.

- res/layout/ → Archivos XML con los diseños de pantalla.

- MainActivity.kt → Actividad principal que actúa como contenedor de fragments.


---

