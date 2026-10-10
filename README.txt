SISTEMA DE SALUD - PARCIAL INTEGRADO
====================================

Proyecto Java Swing para Apache NetBeans.

MÓDULOS
- Inicio de sesión
- Menú principal
- Gestión de pacientes
- Gestión de usuarios
- Gestión de médicos/doctores
- Gestión de citas médicas
- Gestión de medicamentos

MÓDULO DE CITAS MÉDICAS
La clase CRUDCitas permite agregar, buscar, actualizar y eliminar citas.
Los registros se guardan en citas.txt. La clase Cita representa los datos de
cada cita: identificador, DNI del paciente, DNI del doctor, fecha, hora y motivo.

CÓMO ABRIR EN APACHE NETBEANS
1. Descomprime el archivo ZIP.
2. Abre Apache NetBeans y selecciona File > Open Project.
3. Selecciona la carpeta SistemaSalud_Parcial_Integrado.
4. Espera a que NetBeans reconozca el proyecto.
5. Presiona F6 (Run Project).
6. Ingresa las credenciales configuradas en el sistema.

REQUISITO
- JDK compatible con la configuración del proyecto.

Clase principal: Main.
No incluyas credenciales en este archivo ni publiques datos reales de pacientes.

PRUEBA DEL MÓDULO DE CITAS
Se incluye test/PruebaCRUDCitas.java con pruebas básicas de registro, duplicados,
búsqueda, actualización y eliminación. Ejecutarla desde una carpeta de trabajo
temporal, porque el módulo usa el archivo citas.txt relativo al directorio actual.
