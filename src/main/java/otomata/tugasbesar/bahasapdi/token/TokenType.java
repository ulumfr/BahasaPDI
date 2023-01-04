package otomata.tugasbesar.bahasapdi.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TokenType {
    Comment("\\#.*"),
    LineBreak("[\\n\\r]"),
    Whitespace("[\\s\\t]"),
    Keyword("(jika|jikalau|jikatak|sudah|tulis|masukan|kelas|fungsi|kembali|ulang|keluar|selama|by|berhenti|terus|run|bantuan|versi|kredit)(?=\\s|$)"),
    GroupDivider("(\\[|\\]|\\,|\\{|}|[.]{2})"),
    Logical("(true|false)(?=\\s|$)"),
    Numeric("([-]?(?=[.]?[0-9])[0-9]*(?![.]{2})[.]?[0-9]*)"),
    Null("(null)(?=,|\\s|$)"),
    This("(this)(?=,|\\s|$)"),
    Text("\"([^\"]*)\""),
    Operator("(\\+|-|\\*|/{1,2}|%|>=|>|<=|<{1,2}|={1,2}|!=|!|:{2}|\\(|\\)|(new|and|or)(?=\\s|$))"),
    Variable("[a-zA-Z_]+[a-zA-Z0-9_]*");

    private final String regex;
}

