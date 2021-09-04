# Mudlet Docs Extract

Purpose of this project is to extract LUA documentatio into `.lua` files that can be imported
into project (or added to lib path). That way IDE can autocomplete, popup doc and hint parameters of functions.

## VS Code extension
To easily install and configure documentation in VS Code there is extension available:
https://marketplace.visualstudio.com/items?itemName=Delwing.mudlet-scripts-sdk

## Manual VS Code configuration

* Install Lua language server extension (extension id: `sumneko.lua`)
* Download (or sync through Dropbox) directory with generated `lua` files. https://www.dropbox.com/sh/sfqpjl5zune46ut/AAC_vHm0B2hCGt04NXU7A8_Va?dl=0
* Add to `settings.json`
```json
"Lua.workspace.library": {
        "/path/to/downloaded/lua/files/" : true
}
```

![](https://raw.githubusercontent.com/Delwing/mudlet-docs/media/screenshot1.png)

![](https://raw.githubusercontent.com/Delwing/mudlet-docs/media/screenshot2.png)
