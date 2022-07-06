using System.Collections;
using System.Collections.Generic;
using UnityEngine;
[ExecuteInEditMode]


public class Toilet : MonoBehaviour
{

    public GameObject confetti;
    public bool flush;

    private void ToiletHandleDetection()
    {
        if (transform.rotation.z < -0.07)
        {
            flush = true;

        }
        else
        {
            flush = false;

        }

        if (flush)
        {
            confetti.GetComponent<ParticleSystem>().Play();
            confetti.GetComponent<ParticleSystem>().enableEmission = true;
        }
        else confetti.GetComponent<ParticleSystem>().Stop();
    }

    void Update()
    {
        ToiletHandleDetection();
    }
}
