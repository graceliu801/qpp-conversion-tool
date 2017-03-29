package gov.cms.qpp.conversion.encode;

import java.util.List;

import gov.cms.qpp.conversion.model.Encoder;
import gov.cms.qpp.conversion.model.Node;

/**
 * Encoder to serialize numerator data from a Numerator/Denominator Type Measure.
 */
@Encoder(templateId = "2.16.840.1.113883.10.20.27.3.31")
public class AciProportionNumeratorEncoder extends QppOutputEncoder {

	/**
	 *  Encodes an ACI Numerator Measure into the QPP format
	 *
	 * @param wrapper Wrapper that will represent the ACI Numerator Measure
	 * @param node Node that represents the ACI Numerator Measure
	 * @throws EncodeException If an error occurs during encoding
	 */
	@Override
	protected void internalEncode(JsonWrapper wrapper, Node node) throws EncodeException {

		List<Node> children = node.getChildNodes();

		if (!children.isEmpty()) {
			Integer numeratorValue = encodeChild(children.get(0));

			if (null != numeratorValue) {
				wrapper.putObject("numerator", numeratorValue);
			}
		}
	}

	private Integer encodeChild(Node numeratorValueNode) {
		JsonOutputEncoder numeratorValueEncoder = encoders.get(numeratorValueNode.getId());

		JsonWrapper jsonWrapper = new JsonWrapper();
		numeratorValueEncoder.encode(jsonWrapper, numeratorValueNode);

		return jsonWrapper.getInteger("value");
	}
}
