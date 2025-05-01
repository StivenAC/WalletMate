# WalletMate



**WalletMate** es una aplicación de control financiero desarrollada con **Android Kotlin**, **XML** y arquitectura **MVVM**. Esta app ayuda a los usuarios a gestionar sus finanzas, realizar un seguimiento de ingresos y gastos, y visualizar sus datos financieros mediante gráficos interactivos.

---

## 📸 Capturas de Pantalla

| Pantalla de Inicio | Inicio de Sesión | Registro |
|--------------------|------------------|----------|
| <img src="https://i.postimg.cc/3dLbRFqg/splash-screen.png" width="250"/> | <img src="https://i.postimg.cc/jwdszXsR/a11.png" width="250"/> | <img src="https://i.postimg.cc/TKgYG17v/a12.png" width="250"/> |

| Pantalla Principal | Transacciones | Perfil |
|--------------------|---------------|--------|
| <img src="https://i.postimg.cc/1gSRzS2n/a4.png" width="250"/> | <img src="https://i.postimg.cc/p9BWh7M0/a8.png" width="250"/> | <img src="https://i.postimg.cc/mzXTVHDC/a7.png" width="250"/> |

| Gráfico de Ingresos/Gastos | Detalles de Transacción | Actualización de Moneda en Perfil |
|----------------------------|--------------------------|-----------------------------------|
| <img src="https://i.postimg.cc/Th1d1C79/a9.png" width="250"/> | <img src="https://i.postimg.cc/F169L3wQ/a10.png" width="250"/> | <img src="https://i.postimg.cc/H8rdNkcK/a6.png" width="250"/> |

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
   git clone https://github.com/Progambler227788/Finance_Tracker_App.git
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

## 🧑‍💻 Contribuciones

¡Las contribuciones son bienvenidas! Si deseas colaborar:
1. Haz un fork del repositorio.  
2. Crea una nueva rama (`git checkout -b feature/TuNuevaFuncionalidad`).  
3. Realiza tus cambios y haz commit (`git commit -m 'Agrega una nueva funcionalidad'`).  
4. Haz push a tu rama (`git push origin feature/TuNuevaFuncionalidad`).  
5. Abre un Pull Request.

---

## 🙏 Agradecimientos

- [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) por los gráficos interactivos.  
- [Firebase](https://firebase.google.com/) por la autenticación.  
- [Android Developers](https://developer.android.com/) por la documentación oficial.
