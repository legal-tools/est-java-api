# est-java-api
This is an example of an implementation of the eSignTools API.

See https://doc.esign-tools.fr for more details.

Based on REST principles, our Web API return data in JSON .
A document which will be signed is also called "agreement".

The classic workflow is :
- login : send the API credentials and get a token for the next API calls.
- createAgreement : uploads the PDF and set the agreements settings. Theses settings are : title, callback url and recipients. The callback URL is called when an event occurs (document signed or rejected).

The document is now sent for signature to the recipients.

eSignTools will call the callback each time an event occurs.

Example, if the callback is set to https://mysite.com/event :
- https://mysite.com/event?id=1234&eventType=ESIGNED will be called when the document 1234 is signed by all recipients
- https://mysite.com/event?id=1234&eventType=REJECTED will be called when the document 1234 has been rejected by a recipient
- https://mysite.com/event?id=1234&eventType=OUT_FOR_SIGNATURE will be called when the document 1234 has been signed by a recipient but eSignTools is still waiting for the signature of another recipient.

### How to name signature fields in the PDF :
These fields shall have a name like SignatorySignature_X_Y, where X is the recipent position (set in CreateRecipientDTO) and Y the fields number.
*Examples :*
- A document with a single field for one recipient :
  - SignatorySignature_1_1
- A document with 2 fields for one recipient :
  - SignatorySignature_1_1
  - SignatorySignature_1_2
- A document for 2 recipients each with 2 fields :
  - SignatorySignature_1_1
  - SignatorySignature_1_2
  - SignatorySignature_2_1
  - SignatorySignature_2_2
