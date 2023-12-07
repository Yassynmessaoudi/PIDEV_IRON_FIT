<?php

namespace App\Service;

use App\Entity\Cabinet;
use Twilio\Rest\Client;

class SMSService
{
    private string $twilioAccountSid;
    private string $twilioAuthToken;
    private string $twilioPhoneNumber;

    public function __construct(string $twilioAccountSid, string $twilioAuthToken, string $twilioPhoneNumber)
    {
        $this->twilioAccountSid = $twilioAccountSid;
        $this->twilioAuthToken = $twilioAuthToken;
        $this->twilioPhoneNumber = $twilioPhoneNumber;
    }

    public function sendCabinetCreationNotification(Cabinet $cabinet, string $phoneNumber): void
    {
        $message = sprintf(
            "Nouveau médecin ajouté: %s %s, Spécialité: %s, Adresse: %s",
            $cabinet->getPrenommed(),
            $cabinet->getNommed(),
            $cabinet->getSpecialite(),
            $cabinet->getAdresse()
        );
        $this->sendSms($phoneNumber, $message);
    }

    private function sendSms(string $to, string $message): void
    {
        $twilio = new Client($this->twilioAccountSid, $this->twilioAuthToken);

        $twilio->messages
            ->create(
                $to,
                [
                    'from' => $this->twilioPhoneNumber,
                    'body' => $message,
                ]
            );
    }
}
