# CelestHub - Complete Documentation

## 1) CelestHub
CelestHub is a HubCore plugin for Paper 1.21.x designed for network environments, featuring:

- Player profile system (local or MongoDB)
- Cross-hub state synchronization via Redis
- Interactive hotbar system
- GUI menus (settings, profile, leaderboard, outfits, trails, gadgets, queue, lottery, timer, selectors)
- Chat manager (mute / slow / clear)
- Build mode, fly, double jump, PvP arena, jukebox
- Scoreboard + Tablist + Rank integration (LuckPerms optional)

**Main class:** `net.kryunek.hub.Celest`  
**API:** `paper-api 1.21.11-R0.1-SNAPSHOT`  
**Java:** `21`

---

## 2) Requirements
- Paper 1.21.x server
- Java 21
- Optional: LuckPerms
- Optional: MongoDB (if `PERSISTENCE.TYPE: MONGO`)
- Optional: Redis (if `NETWORK_SYNC.ENABLED: true`)

---

## 3) Installation
1. Build with Maven (`mvn clean package`) or use the compiled JAR.
2. Place the JAR inside the `plugins/` folder.
3. Start the server to generate configuration files.
4. Configure files in `plugins/Celest/`.
5. Restart the server or use `/celest reload`.

---

## 4) Module Structure (Startup Order)
1. `FileModule` → loads all `.yml` files  
2. `ManagerModule` → initializes managers (profiles, queue, lottery, timers, outfits, trails, chat, sync, etc.)  
3. `CommandModule` → registers commands  
4. `ListenerModule` → registers event listeners  
5. `VisualsModule` → handles scoreboard, tablist, animations  

---

## 5) Configuration Files

### Core
- `core/config.yml` → network, persistence, ranks, timezone  
- `core/messages.yml` → global messages  
- `core/players.yml` → local player data (LOCAL mode only)

### Features
- `features/settings.yml` → hub rules and gameplay settings  
- `features/hotbar.yml` → hotbar items  
- `features/scoreboard.yml` → scoreboard  
- `features/tab.yml` → tablist  
- `features/queue.yml` → queue system  
- `features/lottery.yml` → lottery system  
- `features/gadgets.yml` → gadgets  
- `features/particle.yml` → trails  
- `features/outfit.yml` → cosmetics  
- `features/jukebox.yml` → music system  

### Menus
- `menus/common.yml`  
- `menus/admin_menus.yml`  
- `menus/celest_editor.yml`  
- `menus/editor_menus.yml`  
- `menus/settings_menu.yml`  
- `menus/server_selector.yml`  
- `menus/hub_selector.yml`

---

## 6) Persistence and Networking

### 6.1 Profile Persistence
Configured in `core/config.yml`:

- `PERSISTENCE.ENABLED`
- `PERSISTENCE.TYPE` → `LOCAL` or `MONGO`
- `PERSISTENCE.MONGO.URI`, `DATABASE`, `COLLECTION`

Profiles are automatically saved and persisted on shutdown.

---

### 6.2 Network Sync (Redis)
Configured in `core/config.yml`:

- `NETWORK_SYNC.ENABLED`
- `NETWORK_SYNC.REDIS.HOST`, `PORT`, `PASSWORD`

Channel: `celesthub:sync`

Synced topics:
- QUEUE_STATE
- LOTTERY_STATE
- TIMER_STATE

---

## 7) Commands

### General
- `/celest` → help
- `/celest info`
- `/celest debug`
- `/celest reload`
- `/celest editor`
- `/spawn`
- `/setspawn`

### Player / Hub
- `/settings`
- `/profile` (`/stats`)
- `/leaderboard` (`/top`, `/lb`)
- `/fly` (`/togglefly`)
- `/buildmode`
- `/jukebox` (`/music`, `/jb`)
- `/trail`
- `/outfit`

### Chat
- `/chat clear`
- `/chat mute`
- `/chat unmute`
- `/chat slow <seconds|off>`

### Queue
- `/queue join <queue>`
- `/queue leave <queue>`
- `/queue pause <queue>`
- `/queue list`
- `/queue manager`

### Lottery
- `/lottery create <name> <seconds>`
- `/lottery start <name>`
- `/lottery end <name>`
- `/lottery list`
- `/lottery join <lottery>`
- `/lottery manager`

### Timer
- `/timer create <queue> <seconds> <prefix>`
- `/timer remove <queue>`
- `/timer list`
- `/timer manager`

---

## 8) Permissions

### Admin
- `celest.command.chat`
- `celest.command.chat.clear`
- `celest.command.chat.mute`
- `celest.command.chat.unmute`
- `celest.command.chat.slow`
- `celest.command.editor`
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

### Chat bypass
- `celest.chat.bypass.pause`
- `celest.chat.bypass.mute`
- `celest.chat.bypass.slow`

### Cosmetics
- `celest.cosmetics.outfit.*`
- `celest.cosmetics.trail.*`
- `celest.gadget.*`

---

## 9) Core Behavior

### Join system
- Teleport to spawn
- Apply hotbar, scoreboard, tablist
- Play join effects
- Enable fly if allowed

### Permission audit
Automatically removes features when permissions are lost:
- fly
- build mode
- cosmetics

### Double jump
Configurable in `features/settings.yml`

### PvP arena
Region-based PvP system with configurable kits

---

## 10) Recommended Workflow
1. Configure `core/config.yml`
2. Set permissions
3. Adjust menus and hotbar
4. Create queues, lotteries, timers
5. Test full server flow:
   - join system
   - GUI menus
   - queue/lottery/timer
   - sync between hubs

---

## 11) Troubleshooting

### Fly not working
- Check permission `celest.command.fly`
- Verify config settings

### Sync not working
- Check Redis credentials
- Ensure `NETWORK_SYNC.ENABLED: true`

### Profiles not saving
- Check persistence config
- Verify MongoDB setup

### Scoreboard / Tab missing
- Check feature configs
- Ensure placeholders/dependencies
