package com.twilio.phonetree.ivr;

import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Say;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IVREndpoints {

    @RequestMapping(value = "/welcome")
    public VoiceResponse welcome() {
        return new VoiceResponse.Builder()
                .gather(new Gather.Builder()
                        .action("/menu")
                        .inputs(Gather.Input.SPEECH)
                        // "collection" or "delivery" (secretly they could also say "sparkles")
                        .say(amySay("""
                            Hello, you're through to the Party Cookies store.
                            What are you calling for today?
                            Say "collection" or "delivery".
                        """))
                        .build())
                .build();
    }

    @RequestMapping(value = "/menu")
    public VoiceResponse menu(@RequestParam("SpeechResult") String gatheredSpeech) {

        return switch(gatheredSpeech.toLowerCase()){
            case "delivery"   -> getDelivery();
            case "collection" -> getCollection();
            case "sparkles"   -> getSecretSparkles();
            default           -> welcome();
        };
    }

    private VoiceResponse getDelivery() {
        String message = """
                The kitchen is baking as quickly as possible for the holiday season.
                Your cookies will be delivered within 2 hours, with a dash of magic that will blow your mind.
                In the meantime, prepare your taste buds.
                The kitchen appreciates your patience.
                """;
        return getVoiceResponse(message);
    }

    private VoiceResponse getCollection() {
        String message = """
                Congratulations, you're about to experience cookie perfection!
                I've got your batch ready and waiting for pickup.
                Just a heads up, after one bite, you might question every cookie you've ever had before.
                Swing by whenever you're ready to upgrade your taste buds.
                """;
        return getVoiceResponse(message);
    }

    private VoiceResponse getSecretSparkles() {
        String message = """
                Oh, you've heard whispers about the legendary secret holiday menu, have you?
                Well, you're in luck because today is your lucky day!
                Buckle up for a taste adventure that transcends ordinary holidays.
                But fair warning, once you experience it, the regular holiday fare might feel a bit lackluster.
                Ask and you shall receive, my friend. Prepare to be dazzled by our exclusive holiday magic!
                """;
        return getVoiceResponse(message);
    }

    private VoiceResponse getVoiceResponse(String message) {
        return new VoiceResponse.Builder()
                .say(amySay(message)).build();
    }

    private Say amySay(String message){
        return new Say.Builder(message)
                .voice(Say.Voice.POLLY_AMY)
                .language(Say.Language.EN_GB)
                .build();
    }
}
