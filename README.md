# djinqPing

A lightweight Paper plugin for checking player ping with `/ping`.

Made by djinq ~ [djinq.dev](https://djinq.dev)

## Installation

1. Download `djinqPing.jar`.
2. Place it in your server `plugins` folder.
3. Restart the server.

## Commands

| Command | Description |
| --- | --- |
| `/ping` | Shows your ping. |
| `/ping <player>` | Shows an online player's ping. |
| `/ping reload` | Reloads `messages.yml`. |

## Permission

| Permission | Description | Default |
| --- | --- | --- |
| `djinqping.use` | Allows `/ping`. | `true` |
| `djinqping.others` | Allows `/ping <player>`. | `true` |
| `djinqping.reload` | Allows `/ping reload`. | `op` |

## Configuration

`messages.yml` contains the prefix and chat messages. Use `/ping reload` after editing it.

Works with Paper 1.21.11 through 26.3. Java 21 is required for Paper 1.21.11; use the Java version required by your Paper build for newer versions.
