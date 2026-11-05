# djinqPing

A small and lightweight ping command plugin for Paper servers.

Check your own ping with `/ping`, or check another online player's ping with `/ping <player>`. Messages and prefix are easy to edit.

Made by djinq ~ [djinq.dev](https://djinq.dev)

## Installation

1. Drop `djinqPing.jar` into your server's `plugins` folder.
2. Start the server once.
3. Edit `plugins/djinqPing/messages.yml` however you want.
4. Run `/ping reload` or restart the server.

## Commands

| Command | Description |
| --- | --- |
| `/ping` | Shows your ping. |
| `/ping <player>` | Shows an online player's ping. |
| `/ping reload` | Reloads `messages.yml`. |

## Permissions

| Permission | Description |
| --- | --- |
| `djinqping.use` | Allows `/ping`. |
| `djinqping.others` | Allows `/ping <player>`. |
| `djinqping.reload` | Allows `/ping reload`. |

## Configuration

`messages.yml` contains one `prefix` setting. It is added automatically to every chat message. Use `/ping reload` after editing it.

Built for Paper 1.21.11 through 26.3. Use Java 21 for Paper 1.21.11, or Java 25 for Paper 26.1+.
