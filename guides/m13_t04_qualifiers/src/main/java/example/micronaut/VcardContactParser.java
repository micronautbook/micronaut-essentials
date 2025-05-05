package example.micronaut;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.property.Email;
import ezvcard.property.Url;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Named("vcard")
@Singleton
class VcardContactParser implements ContactParser {
    private static final Logger LOG = LoggerFactory.getLogger(VcardContactParser.class);

    @Override
    public Optional<Contact> parse(InputStream inputStream) {
        try {
            VCard vcard = Ezvcard.parse(inputStream).first();
            return Optional.of(new Contact(vcard.getStructuredName().getGiven() + " " + vcard.getStructuredName().getFamily(),
                    vcard.getUrls().stream().map(Url::getValue).findFirst().orElse(""),
                    vcard.getEmails().stream().map(Email::getValue).findFirst().orElse("")));
        } catch (IOException e) {
            LOG.error("Failed to parse vcard", e);
        }

        return Optional.empty();
    }
}
