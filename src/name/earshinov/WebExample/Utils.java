package name.earshinov.WebExample;

public final class Utils {

	private Utils() { }

	public static int getIntegerArgument(String urlParameterName, String urlParameterValue) throws HandlingException {
		if (urlParameterValue == null)
			throw new HandlingException("Не передан аргумент \"1\"");

		try {
			return Integer.parseInt(urlParameterValue);
		}
		catch (NumberFormatException e) {
			throw new HandlingException("Аргумент \"" + urlParameterName + "\" не число");
		}
	}

}
