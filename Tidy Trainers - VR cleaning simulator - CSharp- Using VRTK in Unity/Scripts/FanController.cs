/// <summary>
/// Aziz
/// script that changes fan speed and turns it off after 3 pulls & connects springs to held object
/// </summary>
namespace VRTK.Examples
{
    using UnityEngine;
    namespace WestgateVRTS
    {
        public class FanController : MonoBehaviour
        {
            private VRTK_ControllerReference controllerReference;
            public VRTK_InteractableObject linkedObject;
            public Transform cordBase;
            protected Rigidbody[] cordRigidBodies = new Rigidbody[0];
            bool fanTick = false;                               // when it reaches distance 
            [SerializeField] GameObject accessFan;
            float fastenFan = 70f;
            public float fanRotSpeed = 70f;
            Vector3 fanRotAxis = Vector3.up;

            private void Start()
            {
                float distance = (transform.position - cordBase.transform.position).magnitude;
            }
            private void Update()
            {
                accessFan.transform.Rotate(fanRotAxis, fanRotSpeed * Time.deltaTime);

                if (fanRotSpeed >= 220f) //after the third tick it turns off
                {
                    fanRotSpeed = 0f;
                }

                float distance = (transform.position - cordBase.transform.position).magnitude;
                if (distance > 0.1f)
                {
                    fanTick = true;
                }
                else
                {
                    fanTick = false;
                }
            }

            protected virtual void OnEnable()
            {
                linkedObject = (linkedObject == null ? GetComponent<VRTK_InteractableObject>() : linkedObject);

                if (linkedObject != null)
                {
                    linkedObject.InteractableObjectGrabbed += InteractableObjectGrabbed;
                    linkedObject.InteractableObjectUngrabbed += InteractableObjectUngrabbed;
                }

                cordRigidBodies = transform.parent.GetComponentsInChildren<Rigidbody>();
            }

            protected virtual void OnDisable()
            {
                if (linkedObject != null)
                {
                    linkedObject.InteractableObjectGrabbed -= InteractableObjectGrabbed;
                    linkedObject.InteractableObjectUngrabbed -= InteractableObjectUngrabbed;
                }
            }

            protected virtual void InteractableObjectUngrabbed(object sender, InteractableObjectEventArgs e)
            {
                ToggleKinematics(false);

                if (fanTick)
                {
                    fanRotSpeed = fanRotSpeed + fastenFan; //held, pulled and ungrabbed

                }
            }

            protected virtual void InteractableObjectGrabbed(object sender, InteractableObjectEventArgs e)
            {
                ToggleKinematics(false);
                /*
                if (fanTick)
                {
                    Debug.Log("maybe an indicator (haptic?) to let user know it's been ticked");
                }
                */
            }
            protected virtual void ToggleKinematics(bool state)
            {
                foreach (Rigidbody elementRigidbody in cordRigidBodies)
                {
                    elementRigidbody.isKinematic = state;

                }
            }
        }
    }
}