# Mudlet Docs Extract
The purpose of this project is to extract LUA documentation into `.lua` files that can be
imported into a project (or added to the lib path). This allows the IDE to provide
autocomplete, documentation pop-ups, and parameter hints for functions.

## VS Code extension
To easily install and configure documentation in VS Code there is an extension available:
https://marketplace.visualstudio.com/items?itemName=Delwing.mudlet-scripts-sdk

## Manual VS Code configuration

* Install the Lua language server extension (extension id: `sumneko.lua`)
* Download (or sync through Dropbox) the directory with generated `lua` files. https://www.dropbox.com/sh/sfqpjl5zune46ut/AAC_vHm0B2hCGt04NXU7A8_Va?dl=0
* Add it to `settings.json`
```json
"Lua.workspace.library": {
        "/path/to/downloaded/lua/files/" : true
}
```

![](https://raw.githubusercontent.com/Delwing/mudlet-docs/media/screenshot1.png)

![](https://raw.githubusercontent.com/Delwing/mudlet-docs/media/screenshot2.png)
