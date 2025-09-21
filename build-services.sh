#!/bin/bash

# Salir si ocurre un error
set -e

echo "=================================================="
echo "🚀 Iniciando build y despliegue de microservicios"
echo "=================================================="
echo

# Lista de servicios
services=("inventory-service" "notification-service" "order-service")

# Tiempo inicial total
start_total=$(date +%s)

# Mostrar directorio actual
echo "📂 Directorio inicial: $(pwd)"
echo

for service in "${services[@]}"; do
  echo "--------------------------------------------------"
  echo "➡️  Entrando a $service"
  cd "$service"
  echo "📂 Ahora en: $(pwd)"

  echo "🕒 Iniciando build para $service ..."
  start_service=$(date +%s)

  echo "🛠 Ejecutando: mvn clean package -DskipTests"
  mvn clean package -DskipTests

  end_service=$(date +%s)
  duration_service=$((end_service - start_service))

  echo "✅ Build completado para $service"
  echo "📦 JAR generado en: $(pwd)/target/"
  echo "⏱ Tiempo de compilación de $service: ${duration_service}s"

  echo "⬅️  Regresando al directorio raíz"
  cd ..
  echo "📂 Ahora en: $(pwd)"
  echo
done

# Tiempo final total del build
end_total=$(date +%s)
duration_total=$((end_total - start_total))

echo "=================================================="
echo "🎉 Todos los microservicios han sido construidos exitosamente"
echo "⏱ Tiempo total de compilación: ${duration_total}s"
echo "=================================================="
echo

# 🚀 Levantar contenedores con docker-compose en modo detach
echo "--------------------------------------------------"
echo "🐳 Ejecutando: docker-compose up --build -d"
echo "--------------------------------------------------"
docker-compose up --build -d

echo
echo "=================================================="
echo "🎉 Despliegue completado: contenedores corriendo en segundo plano"
echo "📌 Usa 'docker ps' para verlos"
echo "📌 Usa 'docker-compose logs -f' para seguir los logs"
echo "=================================================="
