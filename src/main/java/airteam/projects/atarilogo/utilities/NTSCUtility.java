package airteam.projects.atarilogo.utilities;

import java.awt.Color;

public class NTSCUtility {
	private static String[] NTSCHexColorCodes = {"#000000", "#404040", "#6C6C6C", "#909090", "#B0B0B0", "#C8C8C8", "#DCDCDC", "#ECECEC", "#444400", "#646410", "#848424", "#A0A034", "#B8B840", "#D0D050", "#E8E85C", "#FCFC68", "#702800", "#844414", "#985C28", "#AC783C", "#BC8C4C", "#CCA05C", "#DCB468", "#ECC878", "#841800", "#983418", "#AC5030", "#C06848", "#D0805C", "#E09470", "#ECA880", "#FCBC94", "#880000", "#9C2020", "#B03C3C", "#C05858", "#D07070", "#E08888", "#ECA0A0", "#FCB4B4", "#78005C", "#8C2074", "#A03C88", "#B0589C", "#C070B0", "#D084C0", "#DC9CD0", "#ECB0E0", "#480078", "#602090", "#783CA4", "#8C58B8", "#A070CC", "#B484DC", "#C49CEC", "#D4B0FC", "#140084", "#302098", "#4C3CAC", "#6858C0", "#7C70D0", "#9488E0", "#A8A0EC", "#BCB4FC", "#000088", "#1C209C", "#3840B0", "#505CC0", "#6874D0", "#7C8CE0", "#90A4EC", "#A4B8FC", "#00187C", "#1C3890", "#3854A8", "#5070BC", "#6888CC", "#7C9CDC", "#90B4EC", "#A4C8FC", "#002C5C", "#1C4C78", "#386890", "#5084AC", "#689CC0", "#7CB4D4", "#90CCE8", "#A4E0FC", "#003C2C", "#1C5C48", "#387C64", "#509C80", "#68B494", "#7CD0AC", "#90E4C0", "#A4FCD4", "#003C00", "#205C20", "#407C40", "#5C9C5C", "#74B474", "#8CD08C", "#A4E4A4", "#B8FCB8", "#143800", "#345C1C", "#507C38", "#6C9850", "#84B468", "#9CCC7C", "#B4E490", "#C8FCA4", "#2C3000", "#4C501C", "#687034", "#848C4C", "#9CA864", "#B4C078", "#CCD488", "#E0EC9C", "#442800", "#644818", "#846830", "#A08444", "#B89C58", "#D0B46C", "#E8CC7C", "#FCE08C"};

	public static Color getAtariColorFromNumber(int number) {
		if(number < 0) number = 0;
		if(number > 127) number = 127;
		return Color.decode(NTSCHexColorCodes[number]);
	}
}
