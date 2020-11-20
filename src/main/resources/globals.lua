--- This variable holds the current user command, i.e. unchanged by any aliases or triggers. This is typically used in alias scripts.
command	= ""

--- This variable holds the content of the current line as being processed by the trigger engine. The engine runs all triggers on each line as it arrives from the MUD.
line = ""

---	This Lua table is being used by Mudlet in the context of triggers that use Perl regular expressions.
--- matches[1] holds the entire match, matches[2] holds the first capture group, matches[n] holds the nth-1 capture group. If the Perl trigger indicated 'match all' (same effect as the Perl /g switch) to evaluate all possible matches of the given regex within the current line, matches[n+1] will hold the second entire match, matches[n+2] the first capture group of the second match and matches[n+m] the m-th capture group of the second match.
matches = {}

--- This table is being used by Mudlet in the context of multiline triggers that use Perl regular expression. It holds the table matches[n] as described above for each Perl regular expression based condition of the multiline trigger. multimatches[5][4] may hold the 3rd capture group of the 5th regex in the multiline trigger. This way you can examine and process all relevant data within a single script.
multimatches = {{}}

--- Contains translations of some common texts (right now, exit directions only) that are helpful to you in Lua scripting, as well as the current language selected for the user interface. - See translateTable()
mudlet.translations = {}

--- Makes your life easier when creating new keybindings via Lua by translating the key name into the number needed - see tempKey().
mudlet.key = ""

--- Same as mudlet.key, but for keyboard modifiers - Ctrl, Alt, etc.
mudlet.keymodifier = ""

--- Lists special functionality that the users Mudlet supports - right now, just mudlet.supports.coroutines is listed. Use mudlet.supports to conditionally enable functionality as it's available on the users Mudlet.
mudlet.supports = {}

--- Color definitions used by Geyser, cecho, and many other functions - see showColors(). The profile's color preferences are also accessible under the ansi_ keys.
color_table = {}

db = {}
yajl = {}
lfs = {}
datetime = {}
gmcp = {}

speedWalkPath = ""
speedWalkDir = ""

rex = {}
Geyser = {}