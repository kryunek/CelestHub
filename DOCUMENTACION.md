# CelestHub - Documentacion Completa

## 1) Resumen
CelestHub es un plugin HubCore para Paper 1.21.x orientado a redes con:
- Sistema de perfiles (local o MongoDB)
- Sincronizacion de estado entre hubs por Redis
- Hotbar interactiva
- Menus GUI (settings, profile, leaderboard, outfits, trails, gadgets, queue, lottery, timer, selectors)
- Chat manager (mute/slow/clear)
- Build mode, fly, doble salto, pvp arena, jukebox
- Scoreboard + Tablist + Rank integration (LuckPerms opcional)

Main class: `net.kryunek.hub.Celest`  
API: `paper-api 1.21.11-R0.1-SNAPSHOT`  
Java: `21`

## 2) Requisitos
- Servidor Paper 1.21.x
- Java 21
- Opcional: LuckPerms
- Opcional: MongoDB (si `PERSISTENCE.TYPE: MONGO`)
- Opcional: Redis (si `NETWORK_SYNC.ENABLED: true`)

## 3) Instalacion
1. Compilar con Maven (`mvn clean package`) o usar el jar generado.
2. Copiar el jar a `plugins/`.
3. Iniciar servidor para generar configs.
4. Editar configs en `plugins/Celest/...`.
5. Reiniciar o usar `/celest reload`.

## 4) Estructura de Modulos (arranque)
Orden de carga por prioridad:
1. `FileModule`: carga todos los `.yml`
2. `ManagerModule`: inicializa managers (profiles, queue, lottery, timers, outfits, trails, chat, network sync, etc.)
3. `CommandModule`: registra comandos
4. `ListenerModule`: registra listeners
5. `VisualsModule`: scoreboard/tablist/animaciones

## 5) Archivos de Configuracion
### Core
- `core/config.yml`: red, persistencia, rank system, zona horaria
- `core/messages.yml`: mensajes globales
- `core/players.yml`: datos locales de jugadores (modo LOCAL)

### Features
- `features/settings.yml`: reglas hub (anti damage, walk speed, join title/sound, double jump, pvp arena, permission audit)
- `features/hotbar.yml`: items del hotbar
- `features/scoreboard.yml`: scoreboard
- `features/tab.yml`: tablist
- `features/queue.yml`: colas
- `features/lottery.yml`: sorteos
- `features/gadgets.yml`: gadgets
- `features/particle.yml`: trails
- `features/outfit.yml`: outfits
- `features/jukebox.yml`: musica

### Menus
- `menus/common.yml`
- `menus/admin_menus.yml`
- `menus/celest_editor.yml`
- `menus/editor_menus.yml`
- `menus/settings_menu.yml`
- `menus/server_selector.yml`
- `menus/hub_selector.yml`

## 6) Persistencia y Red
## 6.1 Persistencia de perfiles
En `core/config.yml`:
- `PERSISTENCE.ENABLED`
- `PERSISTENCE.TYPE`: `LOCAL` o `MONGO`
- `PERSISTENCE.MONGO.URI`, `DATABASE`, `COLLECTION`

`ProfileManager` hace autosave periodico y save en shutdown.

## 6.2 Network Sync (Redis)
En `core/config.yml`:
- `NETWORK_SYNC.ENABLED`
- `NETWORK_SYNC.REDIS.HOST`, `PORT`, `PASSWORD`

Canal usado: `celesthub:sync`  
Topicos sincronizados:
- `QUEUE_STATE`
- `LOTTERY_STATE`
- `TIMER_STATE`

## 7) Comandos
Nota: algunos subcomandos se manejan por registro interno (`base.sub`) y otros por parseo de argumentos.

### 7.1 Generales
- `/celest` - ayuda principal
- `/celest info`
- `/celest debug`
- `/celest reload`
- `/celest editor`
- `/spawn`
- `/setspawn`

### 7.2 Jugador / Hub
- `/settings`
- `/profile` (`/stats`)
- `/leaderboard` (`/top`, `/lb`)
- `/fly` (`/togglefly`)
- `/buildmode`
- `/jukebox` (`/music`, `/jb`)
- `/trail` (`/particles`, `/particle`)
- `/outfit` (`/outfits`)

### 7.3 Chat
- `/chat`
- `/chat clear`
- `/chat mute`
- `/chat unmute`
- `/chat slow <seconds|off>`

### 7.4 Queue
- `/queue`
- `/queue join <queue>`
- `/queue leave <queue>`
- `/queue pause <queue>`
- `/queue list`
- `/queue manager`

### 7.5 Lottery
- `/lottery` (`/loteria`, `/cupon`, `/coupon`)
- `/lottery join <lottery>`
- `/lottery list`
- `/lottery create <name> <seconds>`
- `/lottery reward <name> <command>`
- `/lottery winners <name> <count>`
- `/lottery start <name>`
- `/lottery end <name>`
- `/lottery manager`

### 7.6 Timer
- `/timer`
- `/timer create <queue> <seconds> <prefix>`
- `/timer remove <queue>`
- `/timer list`
- `/timer manager`

## 8) Permisos
### 8.1 Administracion/comandos
- `celest.command.chat`
- `celest.command.chat.clear`
- `celest.command.chat.mute`
- `celest.command.chat.unmute`
- `celest.command.chat.pause`
- `celest.command.chat.slow`
- `celest.command.editor`
- `celest.command.editor.chat`
- `celest.command.editor.hotbar`
- `celest.command.debug`
- `celest.command.reload`
- `celest.command.buildmode`
- `celest.command.fly`
- `celest.command.setspawn`
- `celest.command.queue.pause`
- `celest.command.timer.create`
- `celest.command.timer.remove`
- `celest.command.outfit.manager`
- `celest.command.trailparticles.manager`

### 8.2 Bypass chat
- `celest.chat.bypass.pause`
- `celest.chat.bypass.mute`
- `celest.chat.bypass.slow`

### 8.3 Cosmetics/globales
- `celest.cosmetics.outfit.*`
- `celest.cosmetics.trail.*`
- `celest.gadget.*`

## 9) Comportamiento clave
- Join:
  - aplica spawn, hotbar/buildmode, preferencia de tiempo, tab/scoreboard, sonido/titulo
  - si `flyOnJoin` esta activo y tiene permiso, activa vuelo
- Permission audit:
  - desactiva auto opciones cuando el jugador pierde permisos (fly/buildmode/outfit/trail/gadget)
- Double jump:
  - configurable en `features/settings.yml`
- Pvp arena:
  - configurable por mundo, region, kit/effects

## 10) Operacion diaria recomendada
1. Configurar `core/config.yml` (persistencia + redis)
2. Verificar permisos de staff/rangos
3. Ajustar `hotbar.yml`, `settings_menu.yml`, `scoreboard.yml`, `tab.yml`
4. Crear queues/lotteries/timers base
5. Probar flujo completo:
   - join/quit
   - settings (fly, tab, scoreboard)
   - queue/lottery/timer
   - network sync entre dos hubs

## 11) Troubleshooting rapido
- Fly no funciona:
  - revisar permiso `celest.command.fly`
  - revisar `settings_menu.yml` y estado `flyOnJoin` del perfil
- No sincroniza queue/lottery/timer:
  - revisar Redis host/port/password
  - verificar `NETWORK_SYNC.ENABLED: true`
- Perfil no persiste:
  - revisar `PERSISTENCE.ENABLED`
  - si MONGO, validar URI/credenciales/coleccion
- Scoreboard/Tablist no aparecen:
  - revisar archivos `features/scoreboard.yml` y `features/tab.yml`
  - validar placeholders/dependencias externas (si aplica)
