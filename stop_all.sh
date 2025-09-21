#!/bin/bash

# Salir si ocurre un error
set -e

echo "=================================================="
echo "🛑 Deteniendo todos los microservicios y contenedores"
echo "=================================================="
echo

# Detener y eliminar contenedores, redes y volúmenes creados por docker-compose
docker-compose down

echo
echo "=================================================="
echo "🧹 Eliminando directorios 'target' de cada microservicio"
echo "=================================================="

services=("inventory-service" "notification-service" "order-service")

for service in "${services[@]}"; do
  target_dir="$service/target"
  if [ -d "$target_dir" ]; then
    echo "🗑 Eliminando $target_dir ..."
    rm -rf "$target_dir"
    echo "✅ $target_dir eliminado"
  else
    echo "⚠️  No existe $target_dir, saltando..."
  fi
done

echo
echo "=================================================="
echo "✅ Todos los contenedores detenidos y directorios target eliminados"
echo "=================================================="
