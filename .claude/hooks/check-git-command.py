"""
Questo Hook blocca tre categorie di operazioni:

Comando	            Perché bloccarlo
git reset --hard	Può eliminare modifiche locali non salvate.
git clean -fd	    Può cancellare file non tracciati.
git push --force	Può riscrivere la cronologia remota.

"""

import sys
import json
import re

data = json.load(sys.stdin)
command = data.get("tool_input", {}).get("command", "")

forbidden_patterns = [
    r"\bgit\s+reset\s+--hard\b",
    r"\bgit\s+clean\s+-[^\s]*f",
    r"\bgit\s+push\b.*--force(?:-with-lease)?"
]

for pattern in forbidden_patterns:
    if re.search(pattern, command):
        print(json.dumps({
            "hookSpecificOutput": {
                "hookEventName": "PreToolUse",
                "permissionDecision": "deny",
                "permissionDecisionReason":
                    "Potentially destructive Git operation. "
                    "Explicit authorization required."
            }
        }))
        sys.exit(0)

sys.exit(0)
